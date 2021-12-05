package com.IlhamJmartMH;
import com.IlhamJmartMH.dbjson.Serializable;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Complaint extends Serializable
{
    public int paymentId;
    public String desc;
    public Date date;
    SimpleDateFormat ComplaintFormat
        = new SimpleDateFormat("dd/MM/yyyy");
    
    public Complaint(String desc){
        this.date = new Date();
        this.desc = desc;
    }
    
    public String toString() {
        String format = ComplaintFormat.format(date);
        return "Complaint{date=" + format + ",desc='" + desc + "'}";
    }
}
