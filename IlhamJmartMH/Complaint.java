package IlhamJmartMH;

public class Complaint extends Recognizable implements FileParser
{
    public int paymentId;
    public String desc;
    public String date;
    
    public Complaint(int id, String desc){
        super(id);
        date = "DateALive";
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
