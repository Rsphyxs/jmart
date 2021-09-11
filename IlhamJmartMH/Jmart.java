package IlhamJmartMH;

public class Jmart
{
    public static void main(String args[])
    {
        System.out.println("Jmart - Muhammad Ilham M S - 1906300813 - Modul 1 ");
        System.out.println("getPromo : " + getPromo());
        System.out.println("getCustomer : " + getCustomer());
        System.out.println("getDiscountPercentage : " + getDiscountPercentage(1000, 900));
        System.out.println("getDiscountedPrice : " + getDiscountPrice(1000,10.0f));
        System.out.println("getOriginalPrice : " + getOriginalPrice(900, 10.0f));
        System.out.println("getComissionMultiplier : " + getComissionMultiplier());
        System.out.println("getAdjustedPrice : " + getAdjustedPrice(1000));
        System.out.println("getAdminFee : " + getAdminFee(1000));
    }
    

    public static int getPromo(){
        return 0;
    }
    
    public static String getCustomer(){
        return "oop";
    }
    
    public static float getDiscountPercentage(int before, int after){
        if(before < after){
            return 0.0f;
        }
        else{
            float fltBefore = before;
            float fltAfter = after;
            float Percentage = (fltBefore-fltAfter)/before*100;
            return (Percentage);
        }
    }
    
    public static int getDiscountPrice(int price, float discountPercentage){
        if(discountPercentage > 100.0f){
            return 0;
        }
        else{
            float fltPrice = price;
            float finalPrice = price - ((discountPercentage * fltPrice)/100);
            int finPrice = (int)finalPrice;
            return (finPrice);
        }
    }
    
    public static int getOriginalPrice(int discountedPrice, float discountPercentage){
        float fltPrice = discountedPrice;
        float originalPrice = (100/(100-(discountPercentage)))*fltPrice;
        int finalPrice = (int)originalPrice;
        return (finalPrice);
    }
    
    public static float getComissionMultiplier(){
        return 0.05f;
    }
    
    public static int getAdjustedPrice(int price){
        float fltPrice = price;
        float finalPrice = fltPrice+(fltPrice*getComissionMultiplier());
        int finPrice = (int)finalPrice;
        return (finPrice);
    }
    
    public static int getAdminFee(int price){
        float fltPrice = price;
        float finalFee = fltPrice*getComissionMultiplier();
        int finFee = (int)finalFee;
        return finFee;
    }
}
