package com.IlhamJmartMH;
import com.IlhamJmartMH.dbjson.Serializable;

import java.util.Date;
import java.util.Calendar;

public abstract class Invoice extends Serializable {
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Date date;

    public enum Rating {
        NONE, BAD, NEUTRAL, GOOD
    }

    public enum Status {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED, DELIVERED
    }

    protected Invoice(int buyerId, int productId) {
        this.date = Calendar.getInstance().getTime();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.complaintId = -1;
    }

    public abstract double getTotalPay(Product product);

}
