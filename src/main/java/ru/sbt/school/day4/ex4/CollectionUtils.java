package ru.sbt.school.day4.ex4;

import java.util.*;

public class CollectionUtils {
    public static<T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List <? extends T> source, T o) {
        return source.indexOf(o);
    }

//    public static <T> List limit(List<T> source, int size) {
//
//    }

    public static <T> void add(List <? super T> source, T o) {
        source.add(o);
    }

    public static<T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static<T> boolean containsAll(List<? extends  T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List <? extends T> c1, List<? extends T> c2) {
        for (Iterator<? extends T> it = c2.iterator(); it.hasNext();){
            if (c1.contains(it.next()))
                return true;
        }
        return false;
    }

    public static <T extends Comparable<? super T>> List<T> range(List<T> list, T min, T max) {
        List<T> returnList = new ArrayList<>();
        if (list == null)
            return returnList;
        Collections.sort(list);
        for (Iterator<T> it = list.iterator(); it.hasNext();){
            T t = it.next();
            if (t.compareTo(min)==0 || (t.compareTo(min) ==1 && t.compareTo(max) ==-1) || t.compareTo(max) == 0 )
                returnList.add(t);
        }
        return returnList;
    }

    public static <T> List<T> range(List<T> list, T min, T max, Comparator comparator) {
        List<T> returnList = new ArrayList<>();
        if (list == null)
            return returnList;
        Collections.sort(list, comparator);
        for (Iterator<T> it = list.iterator(); it.hasNext();){
            T t = it.next();
            if (((Comparable<T>)t).compareTo(min)==0 ||
                    (((Comparable<T>)t).compareTo(min) ==1 && ((Comparable<T>)t).compareTo(max) ==-1) ||
                    ((Comparable<T>)t).compareTo(max) == 0 )
                returnList.add(t);
        }
        return returnList;
    }
}
