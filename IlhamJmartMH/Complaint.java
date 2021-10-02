package IlhamJmartMH;
import java.util.Date;
import java.util.Calendar;

public class Complaint extends Recognizable implements FileParser
{
    public int paymentId;
    public String desc;
    public Date date;
    
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
}
