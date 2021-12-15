package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.*;
import com.IlhamJmartMH.dbjson.JsonAutowired;
import com.IlhamJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/payment")
public class PaymentController implements BasicGetController<Payment> {
    @JsonAutowired(value = Payment.class, filepath = "paymentData.json")

    public static JsonTable<Payment> paymentTable;
    public static ObjectPoolThread<Payment> poolThread;

    public static final long DELIVERED_LIMIT_MS = 10000;
    public static final long ON_DELIVERY_LIMIT_MS = 10000;
    public static final long ON_PROGRESS_LIMIT_MS = 10000;
    public static final long WAITING_CONF_LIMIT_MS = 30000;

    static {
        poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    @GetMapping("/getByAccountId")
    public ArrayList<Payment> getPaymentByAccountId(@RequestParam int buyerId){
        ArrayList<Payment> paymentList = new ArrayList<>();
        for(Payment p : paymentTable){
            if(p.buyerId == buyerId){
                paymentList.add(p);
            }
        }
        return paymentList;
    }

    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id) {
        Payment payment = null;

        for (int i = 0; i < paymentTable.size(); i++) {
            Payment paymentTemp = paymentTable.get(i);
            if (paymentTemp.id == id) {
                payment = paymentTemp;
            }
        }

        if (payment != null) {
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);

            if (lastRecord.status == Invoice.Status.WAITING_CONFIRMATION) {
                Payment.Record record = new Payment.Record(Invoice.Status.ON_PROGRESS, "Payment accepted");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id) {
        Payment payment = null;

        for (int i = 0; i < paymentTable.size(); i++) {
            Payment paymentTemp = paymentTable.get(i);
            if (paymentTemp.id == id) {
                payment = paymentTemp;
            }
        }

        if (payment != null) {
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);

            if (lastRecord.status == Invoice.Status.WAITING_CONFIRMATION) {
                Payment.Record record = new Payment.Record(Invoice.Status.CANCELLED, "Payment cancelled");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan,  @RequestParam int storeId) {
        Account account = null;
        Product product = null;

        for (int i = 0; i < AccountController.accountTable.size(); i++) {
            Account accountTemp = AccountController.accountTable.get(i);
            if (accountTemp.id == buyerId) {
                account = accountTemp;
            }
        }

        for (int i = 0; i < ProductController.productTable.size(); i++) {
            Product productTemp = ProductController.productTable.get(i);
            if (productTemp.id == productId) {
                product = productTemp;
            }
        }

        if (account != null && product != null) {
            Shipment shipment = new Shipment(shipmentAddress, 0, shipmentPlan, null);
            Payment payment = new Payment(buyerId, productId, productCount, shipment, storeId);
            double price = payment.getTotalPay(product);

            if (account.balance >= price) {
                account.balance = account.balance - price;
                payment.history.add(new Payment.Record(Invoice.Status.WAITING_CONFIRMATION, "Waiting for confirmation"));
                paymentTable.add(payment);
                poolThread.add(payment);
                return payment;
            }
        }
        return null;
    }

    public JsonTable<Payment> getJsonTable() {
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(@PathVariable int id, @RequestParam String receipt) {
        Payment payment = null;

        for (int i = 0; i < paymentTable.size(); i++) {
            Payment paymentTemp = paymentTable.get(i);
            if (paymentTemp.id == id) {
                payment = paymentTemp;
            }
        }

        if (payment != null) {
            int size = payment.history.size();
            Payment.Record lastRecord = payment.history.get(size - 1);
            if (lastRecord.status == Invoice.Status.ON_PROGRESS && (!receipt.isBlank())) {
                payment.shipment.receipt = receipt;
                Payment.Record record = new Payment.Record(Invoice.Status.ON_DELIVERY, "On delivery");
                payment.history.add(record);
                return true;
            }
        }
        return false;
    }

    private static boolean timekeeper(Payment payment) {
        Date timeNow = Calendar.getInstance().getTime();
        if (payment.history.size() != 0) {
            Payment.Record lastRecord = payment.history.get(payment.history.size() - 1);
            long timePassed = timeNow.getTime() - lastRecord.date.getTime();
            if (lastRecord.status == Invoice.Status.WAITING_CONFIRMATION && (timePassed > WAITING_CONF_LIMIT_MS)) {
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
                return true;
            } else if ((lastRecord.status == Invoice.Status.ON_PROGRESS) && (timePassed > ON_PROGRESS_LIMIT_MS)) {
                payment.history.add(new Payment.Record(Invoice.Status.FAILED, "FAILED"));
                return true;
            } else if (lastRecord.status == Invoice.Status.ON_DELIVERY && timePassed > ON_DELIVERY_LIMIT_MS) {
                payment.history.add(new Payment.Record(Invoice.Status.DELIVERED, "DELIVERED"));
                return true;
            } else if (lastRecord.status == Invoice.Status.DELIVERED && timePassed > DELIVERED_LIMIT_MS) {
                payment.history.add(new Payment.Record(Invoice.Status.FINISHED, "FINISHED"));
                return true;
            }
        }
        return false;
    }
}
