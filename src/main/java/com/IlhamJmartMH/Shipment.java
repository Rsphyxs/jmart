package com.IlhamJmartMH;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Shipment
{
    public String address;
    public int shipmentCost;
    public byte plan;
    public String receipt;
    public static Plan INSTANT = new Plan((byte) (1<<0));
    public static Plan SAME_DAY = new Plan((byte) (1<<1));
    public static Plan NEXT_DAY = new Plan((byte) (1<<2));
    public static Plan REGULER = new Plan((byte) (1<<3));
    public static Plan KARGO = new Plan((byte) (1<<4));
    public int cost;
    public static final SimpleDateFormat ESTIMATION_FORMAT
    = new SimpleDateFormat("EEE MMMM dd yyyy");
    public Date date;

    static class Plan {
        public final byte bit;
        private Plan(byte bit){
            this.bit = bit;
        }
    }

    public String getEstimatedArrival(Date reference){
        Calendar cal = Calendar.getInstance();
        cal.setTime(reference);
        if (plan == NEXT_DAY.bit || plan == SAME_DAY.bit){
            cal.add(Calendar.DATE, 0);
        }
        else if (plan == NEXT_DAY.bit){
            cal.add(Calendar.DATE, 1);
        }
        else if (plan == REGULER.bit){
            cal.add(Calendar.DATE, 2);
        }
        else if (plan == KARGO.bit){
            cal.add(Calendar.DATE, 5);
        }
        return ESTIMATION_FORMAT.format(cal.getTime());
    }


    public boolean isDuration(Plan reference)
    {
        return (this.plan & reference.bit) == reference.bit;
    }

    public static boolean isDuration(byte object, Plan reference)
    {
        return (object & reference.bit) == reference.bit;
    }

    public Shipment(String address, int shipmentCost, byte plan, String receipt) {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.plan = plan;
        this.receipt = receipt;
    }
}
