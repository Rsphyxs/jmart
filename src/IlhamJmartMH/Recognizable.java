package IlhamJmartMH;

public class Recognizable implements Comparable<Recognizable>
{
    public final int id;

    protected Recognizable(int id)
    {
        this.id = id;
    }

    public int compareTo(Recognizable other) {
        if (id == other.id) {
            return 1;
        } else {
            return 0;
        }
    }
    
    public boolean equals(Object other){
        if(other instanceof Recognizable){
            Recognizable  UcObj = (Recognizable) other;
            if(this.id == UcObj.id){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    
    public boolean equals(Recognizable other){
        if(this.id == other.id){
            return true;
        }
        else{
            return false;
        }
    }

    public static <T extends Recognizable> int getClosingId(Class<T> clazz){
        return 0;
    }

    public static <T extends Recognizable> int setClosingId(Class<T> clazz, int id){
        return 0;
    }
}
