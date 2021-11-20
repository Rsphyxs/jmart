package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.ObjectPoolThread;
import com.IlhamJmartMH.Payment;
import com.IlhamJmartMH.dbjson.JsonTable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class PaymentController implements BasicGetController<Payment> {
    public static final long DELIVERED_LIMIT_MS = 0;
    public static final long ON_DELIVERY_LIMIT_MS = 0;
    public static final long ON_PROGRESS_LIMIT_MS = 0;
    public static final long WAITING_CONF_LIMIT_MS = 0;
    public static JsonTable<Payment> paymentTable = null;
    public static ObjectPoolThread<Payment> poolThread = null;

    static{
        poolThread = new ObjectPoolThread<Payment>("Thread", PaymentController::timekeeper);
        poolThread.start();
    }

    @PostMapping("/{id}/accept")
    public boolean accept(@PathVariable int id){
        return true;
    }

    @PostMapping("/{id}/cancel")
    public boolean cancel(@PathVariable int id){
        return true;
    }

    @PostMapping("/create")
    public Payment create(@RequestParam int buyerId, @RequestParam int productId, @RequestParam int productCount, @RequestParam String shipmentAddress, @RequestParam byte shipmentPlan){
        return null;
    }

    public JsonTable<Payment> getJsonTable(){
        return paymentTable;
    }

    @PostMapping("/{id}/submit")
    public boolean submit(int id,String receipt){
        return true;
    }

    private static boolean timekeeper(Payment payment){
        return true;
    }
}
