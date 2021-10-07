package IlhamJmartMH;

public interface FileParser
{
    boolean read (String content);
    
    default Object write() {
        return null;
    }
    
    static Object newInstance (String content){
        return null;
    }
}
