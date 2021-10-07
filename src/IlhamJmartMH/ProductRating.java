package IlhamJmartMH;

public class ProductRating
{
    private long total;
    private long count;
    public ProductRating()
    {
        this.total = 0;
        this.count = 0;
    }
    
    public void insert(int rating){
        total = total + rating;
        count++;
    }
    
    public double getAverage(){
        double average = total/count;
        return average;
    }
    
    public long getCount(){
        return count;
    }
    
    public long getTotal(){
        return total;
    }
}
