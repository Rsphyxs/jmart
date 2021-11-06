package IlhamJmartMH;

import java.util.HashMap;
import java.util.Map;

public class Serializable implements Comparable<Serializable>
{
    public final int id;
    private static Map<Class<?>, Integer> mapCounter = new HashMap();

    protected Serializable()
    {
        if(mapCounter.get(getClass()) == null){
            mapCounter.put(getClass(), 0);
        }
        else{
            mapCounter.put(getClass(), mapCounter.get(getClass())+1);
        }
        this.id = mapCounter.get(getClass());
    }

    public int compareTo(Serializable other) {
        if (id == other.id) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean equals(Object other){
        if(other instanceof Serializable)
        {
            Serializable recog = (Serializable) other;
            return this.id == recog.id;
        }
        return false;
    }

//    public boolean equals(Serializable other){
//        if(this.id == other.id){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }

    public static <T extends Serializable> int getClosingId(Class<T> clazz){
        return mapCounter.get(clazz);
    }

    public static <T extends Serializable> int setClosingId(Class<T> clazz, int id){
        return mapCounter.put(clazz, id);
    }
}
