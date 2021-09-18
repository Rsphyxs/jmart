package IlhamJmartMH;

public class PriceTag
{
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;
    double discount;
    double price;
    
    public PriceTag(double price)
    {
        this.price = price;
        this.discount = 0.0;
    }
    
    public PriceTag(double price, double discount){
        this.price = price;
        this.discount = discount;
    }

    public double getAdjusedtPrice(){
        return getDiscountedPrice()+getAdminFee();
    }
    
    public double getAdminFee(){
        double adminFee;
        if(getDiscountedPrice() <= BOTTOM_PRICE){
            adminFee = BOTTOM_FEE;
        }
        else{
            adminFee = getDiscountedPrice() - 
            (getDiscountedPrice() * COMMISSION_MULTIPLIER);
        }
        return adminFee;
    }
    
    private double getDiscountedPrice(){
        if(discount >= 100.0){
            return 0.0;
        }
        else{
            return price - ((price * discount)/100);
        }
    }
}
