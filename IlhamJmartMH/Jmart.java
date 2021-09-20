package IlhamJmartMH;

public class Jmart
{
    public static void main(String args[])
    {
        System.out.println("Jmart - Muhammad Ilham M S - 1906300813 - Modul 2 ");
    }
    
    public static Product createProduct(){
        PriceTag priceTag = new PriceTag(15000000);
        Product product = new Product("MSI Prestige 14", 5, false, priceTag, ProductCategory.KITCHEN);
        return product;
    }
    
    public static Coupon createCoupon() {
        return new Coupon("11-11-11", 2122003, Coupon.Type.DISCOUNT, 42, 2100000);
    }

    public static ShipmentDuration createShipmentDuration() {
        return new ShipmentDuration(ShipmentDuration.REGULER, ShipmentDuration.NEXT_DAY);
    }
}
