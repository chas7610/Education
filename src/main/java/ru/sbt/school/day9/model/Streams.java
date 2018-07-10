package ru.sbt.school.day9.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class Streams<T> {
    private  List<T> streams;

    private Streams(List<? extends T> list) {
        this.streams = new ArrayList<>(list);
    }

    public static <T> Streams<T> of(List<T> list){
        return new Streams(list);

    }
    public Streams<T> filter(Predicate<? super T> predicate){
        for (T t: streams){
            if (!predicate.test(t)){
                streams.remove(t);
            }
        }
        return this;
    }

    public Streams<T> transform(Function<? super T, ? extends T> mapper){
        List<T> temp = new ArrayList<T>();
        for (T t: streams){
            T newT =  mapper.apply(t);
            temp.add(newT);
        }
        streams.clear();
        streams.addAll(temp);
        return this;
    }

    public<K, V> Map<K, V> toMap(Function<? super T, ? extends K> keyMapper,
                                 Function<? super T, ? extends V> valueMapper){
        Map<K, V> map = new HashMap<K, V>();
        for (T t : streams){
            K k = keyMapper.apply(t);
            V v = valueMapper.apply(t);
            map.put(k,v);
        }
        return map;
    }

}
