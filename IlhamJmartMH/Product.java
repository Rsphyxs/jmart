package IlhamJmartMH;

class Product extends Recognizable implements FileParser {
    public int storeId;
    public String name;
    public int weight;
    public boolean conditionUsed;
    public PriceTag priceTag;
    public ProductCategory category;
    public ProductRating rating;
    public Shipment.MultiDuration multiDuration;
  
    public Product(int id, int storeId, String name, int weight, boolean conditionUsed,
                   PriceTag priceTag, ProductCategory category, Shipment.MultiDuration multiDuration){
        super(id);
        this.storeId = storeId;
        this.name = name;
        this.weight = weight;
        this.conditionUsed = conditionUsed;
        this.priceTag = priceTag;
        this.category = category;
        this.rating = new ProductRating();
        this.multiDuration = multiDuration;
    }

    public boolean read(String content){
        return false;
    }

    public String toString() {
        return  "Nama : " + this.name + "\nWeight : " + this.weight +
                "\nConditionUsed : " + this.conditionUsed + "\nPriceTag : " + this.priceTag +
                "\nCategory: " + this.category + "\nRating: " + this.rating +
                "\nStoreId: " + this.storeId;
    }
}
