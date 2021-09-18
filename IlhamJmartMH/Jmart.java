package IlhamJmartMH;

public class Jmart
{
    public static void main(String args[])
    {
        System.out.println("Jmart - Muhammad Ilham M S - 1906300813 - Modul 2 ");
    }
    
    public static Product create(){
        PriceTag priceTag = new PriceTag(15000000);
        Product product = new Product("MSI Prestige 14",12,false,priceTag,
        ProductCategory.GAMING);
        return product;
    }
}
