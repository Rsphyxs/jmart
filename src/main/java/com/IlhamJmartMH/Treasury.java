package com.IlhamJmartMH;

public class Treasury
{
    public static final double COMMISSION_MULTIPLIER = 0.05;
    public static final double BOTTOM_PRICE = 20000.0;
    public static final double BOTTOM_FEE = 1000.0;
    double discount;
    double price;
    
    public Treasury(double price)
    {
        this.price = price;
        this.discount = 0.0;
    }
    
    public Treasury(double price, double discount){
        this.price = price;
        this.discount = discount;
    }

    public static double getAdjustedPrice(double price, double discount){
        return getDiscountedPrice(price, discount) + getAdminFee(price, discount);
    }
    
    public double getAdminFee(double price, double discount){
        double adminFee;
        if(getDiscountedPrice(price, discount) <= BOTTOM_PRICE){
            adminFee = BOTTOM_FEE;
        }
        else{
            adminFee = getDiscountedPrice(price, discount) -
            (getDiscountedPrice(price, discount) * COMMISSION_MULTIPLIER);
        }
        return adminFee;
    }
    
    private double getDiscountedPrice(double price, double discount){
        if(discount >= 100.0){
            return 0.0;
        }
        else{
            return price - ((price * discount)/100);
        }
    }
}
