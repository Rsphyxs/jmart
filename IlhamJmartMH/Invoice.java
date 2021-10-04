package IlhamJmartMH;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;

public abstract class Invoice extends Recognizable implements FileParser {
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;
    public Date date;
    public ArrayList<Record> history = new ArrayList<Record>();

    enum Rating {
        NONE, BAD, NEUTRAL, GOOD
    }

    enum Status {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED
    }

    protected Invoice(int id, int buyerId, int productId) {
        super(id);
        this.date = new Date();
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }

    public boolean read(String content) {
        return false;
    }

    public abstract double getTotalPay();
    
    class Record{
        public Status status;
        public Date date;
        public String message;
    }
}
