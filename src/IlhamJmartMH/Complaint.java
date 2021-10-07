package IlhamJmartMH;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Complaint extends Recognizable implements FileParser
{
    public int paymentId;
    public String desc;
    public Date date;
    SimpleDateFormat ComplaintFormat
        = new SimpleDateFormat("dd/MM/yyyy");
    
    public Complaint(int id, String desc){
        super(id);
        this.date = new Date();
        this.desc = desc;
    }
    
    public boolean read(String content){
        return false;
    }
    
    public boolean validate(){
        return false;
    }
    
    public Transactor perform(){
        return null;
    }
    
    public String toString() {
        String format = ComplaintFormat.format(date);
        return "Complaint{date=" + format + ",desc='" + desc + "'}";
    }
}
