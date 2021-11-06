package IlhamJmartMH;



public class Coupon extends Serializable
{
    public final String name;
    public final int code;
    public final double cut;
    public final Type type;
    public final double minimum;
    private boolean used;
    public double price = 123.456;
    public double discount = 50;
    
    public enum Type
{
    DISCOUNT, REBATE;
}
    
    public Coupon(String name, int code, Type type, double cut, double minimum)
    {
        this.name = name;
        this.code = code;
        this.type = type;
        this.cut = cut;
        this.minimum = minimum;
        this.used = false;
    }

    public boolean isUsed()
    {
        return used;
    }
    
    public boolean canApply(Treasury treasury){
        if(treasury.getAdjustedPrice(price, discount) >= minimum && used == false){
            return true;
        }
        else{
            return false;
        }
    }
    
    public double apply(Treasury treasury){
        used = true;
        if (type == type.DISCOUNT){
            return (100 - cut) / 100* treasury.getAdjustedPrice(price, discount);
        }
        else{//type == REBATE
            return treasury.getAdjustedPrice(price, discount) - treasury.price;
        }
    }
}
