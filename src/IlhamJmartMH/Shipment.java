package IlhamJmartMH;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Shipment implements FileParser
{
    public String address;
    public int shipmentCost;
    public Duration duration;
    public String receipt;
    
    static class Duration
    {
        public static Duration INSTANT = new Duration((byte) (1<<0));
        public static Duration SAME_DAY = new Duration((byte) (1<<1));
        public static Duration NEXT_DAY = new Duration((byte) (1<<2));
        public static Duration REGULER = new Duration((byte) (1<<3));
        public static Duration KARGO = new Duration((byte) (1<<4));
        private byte bit;
        public static final SimpleDateFormat ESTIMATION_FORMAT
        = new SimpleDateFormat("EEE MMMM dd yyyy");
        public Date date;

        private Duration(byte bit){
            this.bit = bit;
        }
        
        public String getEstimatedArrival(Date reference){
            Calendar cal = Calendar.getInstance();
            cal.setTime(reference);
            if (bit == Duration.INSTANT.bit || bit == Duration.SAME_DAY.bit){
                cal.add(Calendar.DATE, 0);
            }
            else if (bit == Duration.NEXT_DAY.bit){
                cal.add(Calendar.DATE, 1);
            }
            else if (bit == Duration.REGULER.bit){
                cal.add(Calendar.DATE, 2);
            }
            else if (bit == Duration.KARGO.bit){
                cal.add(Calendar.DATE, 5);
            }
            return ESTIMATION_FORMAT.format(cal.getTime());
        }
    }

    static class MultiDuration {
        public byte bit;
        public MultiDuration(Duration... args){
            byte multiDuration = args[0].bit;
            int i = 0;

            for(i=1; i<args.length; i++){
                multiDuration = (byte) (multiDuration | args[i].bit);
            }

            bit = multiDuration;
        }

        public boolean isDuration(Duration reference){
            return ((this.bit & reference.bit) == reference.bit);
        }
    }

    public Shipment(String address, int shipmentCost, Duration duration, String receipt) {
        this.address = address;
        this.shipmentCost = shipmentCost;
        this.duration = duration;
        this.receipt = receipt;
    }
    
    public boolean read(String content){
        return false;
    }
    
    public Object write(){
        return null;
    }
    
    public static Object newInstance(String content){
        return null;
    }
    
}