package IlhamJmartMH;

public abstract class Invoice extends Recognizable implements FileParser {
    public String date;
    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public Status status;

    enum Rating {
        NONE, BAD, NEUTRAL, GOOD
    }

    enum Status {
        WAITING_CONFIRMATION, CANCELLED, ON_PROGRESS, ON_DELIVERY, COMPLAINT, FINISHED, FAILED
    }

    protected Invoice(int id, int buyerId, int productId) {
        super(id);
        this.date = "DateALive";
        this.buyerId = buyerId;
        this.productId = productId;
        this.rating = Rating.NONE;
        this.status = Status.WAITING_CONFIRMATION;
    }

    public boolean read(String content) {
        return false;
    }

    public abstract double getTotalPay();
}
