package com.IlhamJmartMH;

import java.util.*;

public class Algorithm {
    private Algorithm() {
    }
    public static <T> List<T> collect (T[] array, T value){
        Predicate<T> pred = val -> val.equals(value);
        List<T> var = new ArrayList<>();
        for (int i = 1;  i < array.length; i++){
            if (pred.predicate(array[i])) {
                var.add(array[i]);
            }
        }
        return var;
    }

    public static <T> List<T> collect (Iterable<T> iterable, T value){
        Predicate<T> pred = val -> val.equals(value);
        List<T> var = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while(iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                var.add(element);
            }
        }
        return var;
    }

    public static <T> List<T> collect (Iterator<T> iterator, T value){
        Predicate<T> pred = val -> val.equals(value);
        List<T> var = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                var.add(element);
            }
        }
        return var;
    }

    public static <T> List<T> collect (T[] array, Predicate<T> pred){
        List<T> var = new ArrayList<>();
        for (int i = 1; i< array.length; i++) {
            if (pred.predicate(array[i])) {
                var.add(array[i]);
            }
        }
        return var;
    }

    public static <T> List<T> collect (Iterable<T>iterable, Predicate<T> pred){
        List<T> var = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                var.add(element);
            }
        }
        return var;
    }

    public static <T> List<T> collect (Iterator<T> iterator, Predicate<T> pred){
        List<T> var = new ArrayList<>();

        while (iterator.hasNext()) {
            T element = iterator.next();
            if (pred.predicate(element)) {
                var.add(element);
            }
        }
        return var;
    }

    public static <T> int count(T[] array, T value){
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

    public static <T> int count(Iterable<T> iterable, T value){
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

    public static <T> int count(Iterator<T> iterator, T value){
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

    public static <T> int count(T[] array, Predicate<T> pred){
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

    public static <T> int count(Iterable<T> iterable, Predicate<T> pred){
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

    public static <T> int count(Iterator<T> iterator, Predicate<T> pred){
        int cnt = 0;
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                cnt++;
            }
        }
        return cnt;
    }

    public static <T> boolean exists(T[] array, T value){
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

    public static <T> boolean exists(Iterable<T> iterable, T value){
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

    public static <T> boolean exists(Iterator<T> iterator, T value){
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists(T[] array, Predicate<T> pred){
        int i = 0;
        while(i < array.length){
            if(pred.predicate(array[i])){
                return true;
            }
            i++;
        }
        return false;
    }

    public static <T> boolean exists(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public static <T> boolean exists(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return true;
            }
        }
        return false;
    }

    public static <T> T find(T[] array, T value){
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

    public static <T> T find(Iterable<T> iterable, T value){
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

    public static <T> T find(Iterator<T> iterator, T value){
        Predicate<T> p = value1 -> (value1 == value);
        while(iterator.hasNext()){
            T element = iterator.next();
            if(p.predicate(element)){
                return element;
            }
        }
        return null;
    }

    public static <T> T find(T[] array, Predicate<T> pred){
        int i = 0;
        while(i < array.length){
            if(pred.predicate(array[i])){
                return array[i];
            }
            i++;
        }
        return null;
    }

    public static <T> T find(Iterable<T> iterable, Predicate<T> pred){
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return element;
            }
        }
        return null;
    }

    public static <T> T find(Iterator<T> iterator, Predicate<T> pred){
        while(iterator.hasNext()){
            T element = iterator.next();
            if(pred.predicate(element)){
                return element;
            }
        }
        return null;
    }

    public static <T extends Comparable<? super T >> T max(T first, T  second) {
        if(first.compareTo(second) > 0){
            return first;
        }
        return second;
    }

    public static <T extends Comparable<? super T>> T max(T[] array){
        T max = null;
        if (array.length == 1){
            return array[0];
        }
        else {
            for (int i = 1; i < array.length; i++) {
                if (max == null){
                    max = array[i];
                }
                else if (array[i].compareTo(max) > 0) {
                    max = array[i];
                }
            }
            return max;
        }
    }

    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable){
        Iterator<T> iterator = iterable.iterator();
        T max = null;
        while(iterator.hasNext()){
            T element = iterator.next();
            if (max == null){
                max = element;
            }
            else if(element.compareTo(max) > 0){
                max = element;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator){
        T max = iterator.next();
        while(iterator.hasNext()){
            T element = iterator.next();
            if(element.compareTo(max) > 0){
                max = element;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(T first, T second, Comparator<? super T> comparator){
        int varCompare = comparator.compare(first, second);
        if(varCompare > 0){
            return first;
        }
        else{
            return second;
        }
    }

    public static <T extends Comparable<? super T>> T max(T[] array, Comparator<? super T> comparator){
        T max = array[0];
        for(int i = 0; i < array.length; i++){
            int varCompare = comparator.compare(array[i], max);
            if(varCompare > 0){
                max = array[i];
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterable<T> iterable, Comparator<? super T> comparator){
        Iterator<T> iterator = iterable.iterator();
        T max = iterator.next();
        while(iterator.hasNext()){
            T element = iterator.next();
            int varCompare = comparator.compare(element, max);
            if(varCompare > 0){
                max = element;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T>> T max(Iterator<T> iterator, Comparator<? super T> comparator){
        T max = iterator.next();
        while(iterator.hasNext()){
            T element = iterator.next();
            int varCompare = comparator.compare(element, max);
            if(varCompare > 0){
                max = element;
            }
        }
        return max;
    }

    public static <T extends Comparable<? super T >> T min(T  first, T  second) {
        if(first.compareTo(second) < 0){
            return first;
        }
        return second;
    }

    public static <T extends Comparable<? super T>> T min(T[] array){
        T min = null;
        for (T element : array){
            if (min == null){
                min = element;
            }
            else if (element.compareTo(min) < 0){
                min = element;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable){
        T min = null;
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()){
            T element = iterator.next();
            if (min == null){
                min = element;
            }
            else if (element.compareTo(min) < 0){
                min = element;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator){
        T min = null;
        while (iterator.hasNext()){
            T element = iterator.next();
            if (min == null){
                min = element;
            }
            else if (element.compareTo(min) < 0){
                min = element;
            }
        }
        return min;

    }

    public static <T extends Comparable<? super T>> T min(T first, T second, Comparator<? super T> comparator){
        int varCompare = comparator.compare(first, second);
        if (varCompare < 0){
            return first;
        }
        else{
            return second;
        }
    }

    public static <T extends Comparable<? super T>> T min(T[] array, Comparator<? super T> comparator){
        T min = null;
        for (T element : array){
            if (min == null){
                min = element;
            }
            else{
                int varCompare = comparator.compare(element, min);
                if (varCompare < 0) min = element;
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterable<T> iterable, Comparator<? super T> comparator){
        T min = null;
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()){
            T element = iterator.next();
            if (min == null){
                min = element;
            }
            else{
                int varCompare = comparator.compare(element, min);
                if (varCompare < 0){
                    min = element;
                }
            }
        }
        return min;
    }

    public static <T extends Comparable<? super T>> T min(Iterator<T> iterator, Comparator<? super T> comparator){
        T min = null;
        while (iterator.hasNext()){
            T element = iterator.next();
            if (min == null) {
                min = element;
            }
            else {
                int varCompare = comparator.compare(element, min);
                if (varCompare < 0) min = element;
            }
        }
        return min;
    }

    public static <T> List<T> paginate(T[] array, int page, int pageSize, Predicate<T> pred){
        List<T> newList = new ArrayList<>();
        for(T obj : array)
        {
            if(pred.predicate(obj))
            {
                newList.add(obj);
            }
        }
        int firstIndex = page * pageSize;
        int finalIndex = firstIndex + pageSize;
        List<T> tempList;
        if(finalIndex > newList.size())
        {
            tempList = newList.subList(firstIndex, newList.size());
        }
        else
        {
            tempList = newList.subList(firstIndex, finalIndex);
        }
        return tempList;
    }

    public static <T> List<T> paginate(Iterable<T> iterable, int page, int pageSize, Predicate<T> pred){
        List<T> tempList = new ArrayList<>();
        Iterator<T> iterator = iterable.iterator();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                tempList.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > tempList.size())
        {
            paginatedList = tempList.subList(startIndex,tempList.size());
        }
        else
        {
            paginatedList = tempList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }

    public static <T> List<T> paginate(Iterator<T> iterator, int page, int pageSize, Predicate<T> pred){
        List<T> tempList = new ArrayList<>();
        while(iterator.hasNext())
        {
            T element = iterator.next();
            if(pred.predicate(element))
            {
                tempList.add(element);
            }
        }
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;
        List<T> paginatedList;
        if(endIndex > tempList.size())
        {
            paginatedList = tempList.subList(startIndex, tempList.size());
        }
        else
        {
            paginatedList = tempList.subList(startIndex, endIndex);
        }
        return paginatedList;
    }
}