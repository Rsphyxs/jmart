package IlhamJmartMH;

public class ShipmentDuration
{
    public static ShipmentDuration INSTANT = new ShipmentDuration(1 << 0);
    public static ShipmentDuration SAME_DAY = new ShipmentDuration(1 << 1);
    public static ShipmentDuration NEXT_DAY = new ShipmentDuration(1 << 2);
    public static ShipmentDuration REGULER = new ShipmentDuration(1 << 3);
    public static ShipmentDuration KARGO = new ShipmentDuration(1 << 4);
    private int bit;

    private ShipmentDuration(int bit) {
        this.bit = bit;
    }

    public ShipmentDuration(ShipmentDuration... args) {
        int shipmentDuration = args[0].bit;
        
        for (int i = 1; i < args.length; i++) {
            shipmentDuration = shipmentDuration | args[i].bit;
        }
        
        this.bit = shipmentDuration;
    }

    public boolean isDuration(ShipmentDuration reference) {
        return (this.bit & reference.bit) == reference.bit;
    }
}
