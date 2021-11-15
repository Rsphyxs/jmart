package IlhamJmartMH;

public class PhoneTopUp extends Invoice{
    String phoneNumber;
    Status status;

    public PhoneTopUp(int buyerId, int productId, String phoneNumber)
    {
        super(buyerId, productId);
        this.phoneNumber = phoneNumber;
    }

    @Override
    public double getTotalPay(Product product) {
        return product.price;
    }
}
