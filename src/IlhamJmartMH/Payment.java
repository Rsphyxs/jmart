package IlhamJmartMH;

public class Payment extends Invoice
{
    public Shipment shipment;
    public int productCount;

    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId, productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }
    
    public double getTotalPay() {
        return 0;
    }
}

