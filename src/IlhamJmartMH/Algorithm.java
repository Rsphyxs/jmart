package IlhamJmartMH;

import java.util.*;

public class Algorithm {
    private Algorithm() {
    }

    public <T> int count(T[] array, T value){
        int cnt = 0;
        int i = 0;
        Predicate<T> pred = val -> (val == value);
        while(i < array.length){
            if(pred.predicate(array[i])){
                cnt++;
            }
            i++;
        }
        return cnt;
    }

    public <T> int count(Iterable<T> iterable, T value){
        int cnt = 0;
        Iterator<T> iterator = iterable.iterator();
        Predicate<T> pred = val -> (val == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                cnt++;
            }
        }
        return cnt;
    }

    public <T> int count(Iterator<T> iterator, T value){
        int cnt = 0;
        Predicate<T> pred = val -> (val == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                cnt++;
            }
        }
        return cnt;
    }

    public <T> int count(T[] array, Predicate<T> pred){
        int cnt = 0;
        int i = 0;
        while(i < array.length){
            if(pred.predicate(array[i])){
                cnt++;
            }
            i++;
        }
        return cnt;
    }

    public <T> int count(Iterable<T> iterable, Predicate<T> pred){
        int cnt = 0;
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                cnt++;
            }
        }
        return cnt;
    }

    public <T> int count(Iterator<T> iterator, Predicate<T> pred){
        int cnt = 0;
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                cnt++;
            }
        }
        return cnt;
    }

    public <T> boolean exists(T[] array, T value){
        int i = 0;
        Predicate<T> p = value1 -> (value1 == value);
        while(i < array.length){
            if(p.predicate(array[i])){
                return true;
            }
            i++;
        }
        return false;
    }

    public <T> boolean exists(Iterable<T> iterable, T value){
        Iterator<T> iterator = iterable.iterator();
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(Iterator<T> iterator, T value){
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(T[] array, Predicate<T> pred){
        int i = 0;
        while(i < array.length){
            if(pred.predicate(array[i])){
                return true;
            }
            i++;
        }
        return false;
    }

    public <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public <T> T find(T[] array, T value){
        int i = 0;
        Predicate<T> p = value1 -> (value1 == value);
        while(i < array.length){
            if(p.predicate(array[i])){
                return array[i];
            }
            i++;
        }
        return null;
    }

    public <T> T find(Iterable<T> iterable, T value){
        Iterator<T> iterator = iterable.iterator();
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return element;
            }
        }
        return null;
    }

    public <T> T find(Iterator<T> iterator, T value){
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return element;
            }
        }
        return null;
    }

    public <T> T find(T[] array, Predicate<T> pred){
        int i = 0;
        while(i < array.length){
            if(pred.predicate(array[i])){
                return array[i];
            }
            i++;
        }
        return null;
    }

    public <T> T find(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return element;
            }
        }
        return null;
    }

    public <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return element;
            }
        }
        return null;
    }

    public static <T extends Comparable<? super T >> T max(T first, T  second) {
        if(first.compareTo(second) > 0) {
            return first;
        }
        return second;
    }

    public static <T extends Comparable<? super T >> T min(T  first, T  second) {
        if(first.compareTo(second) < 0) {
            return first;
        }
        return second;
    }
}