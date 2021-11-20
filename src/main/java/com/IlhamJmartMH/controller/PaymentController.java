package com.IlhamJmartMH.controller;

import com.IlhamJmartMH.ObjectPoolThread;
import com.IlhamJmartMH.Payment;
import com.IlhamJmartMH.dbjson.JsonTable;

public class PaymentController {
    public static final long DELIVERED_LIMIT_MS = 0;
    public static final long ON_DELIVERY_LIMIT_MS = 0;
    public static final long ON_PROGRESS_LIMIT_MS = 0;
    public static final long WAITING_CONF_LIMIT_MS = 0;
    public static JsonTable<Payment> paymentTable = null;
    public static ObjectPoolThread<Payment> poolThread = null;

    public boolean accept(int id){
        return true;
    }

    public boolean cancel(int id){
        return true;
    }

    public Payment create(int buyerId, int productId, int productCount, String shipmentAddress, byte shipmentPlan){
        return null;
    }

    public JsonTable<Payment> getJsonTable(){
        return null;
    }

    public boolean submit(int id,String receipt){
        return true;
    }

    private static boolean timekeeper(Payment payment){
        return true;
    }
}
