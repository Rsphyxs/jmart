package IlhamJmartMH;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Payment extends Invoice
{
    public ArrayList<Record> history = new ArrayList<>();
    public int productCount;
    public Shipment shipment;


    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    public double getTotalPay(Product product) {
        return product.price;
    }

    static class Record
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

