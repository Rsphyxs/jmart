package com.IlhamJmartMH;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public int storeId;
    public Shipment shipment;


    public Payment(int buyerId, int productId, int productCount, Shipment shipment, int storeId){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
        this.storeId =storeId;
    }

    public double getTotalPay(Product product) {
        return ((productCount * product.price) + shipment.cost);
    }

    public static class Record
    {
        public Status status;
        public final Date date;
        public String message;

        public Record(Status status, String message)
        {
            this.status = status;
            this.message = message;
            this.date = Calendar.getInstance().getTime();
        }
    }
}

