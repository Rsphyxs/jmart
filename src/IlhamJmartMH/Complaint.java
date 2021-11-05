package IlhamJmartMH;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Complaint
{
    public int paymentId;
    public String desc;
    public Date date;
    SimpleDateFormat ComplaintFormat
        = new SimpleDateFormat("dd/MM/yyyy");
    
    public Complaint(String desc){
        this.date = new Date();
        this.desc = desc;
    }
    
    public String toString() {
        String format = ComplaintFormat.format(date);
        return "Complaint{date=" + format + ",desc='" + desc + "'}";
    }
}
