package ru.sbt.school.day4.ex3;

import java.util.*;

public class CountMapIml<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<T, Integer>();
    private List<T> list = new ArrayList<>();

    public CountMapIml() {
    }

    @Override
    public void add(T t) {
        if (!map.containsKey(t))
            map.put(t, 1);
        else
            map.put(t, map.get(t)+1);
    }

    @Override
    public int getCount(T t) {
        return map.get(t);
    }

    @Override
    public int remove(T t) {
        int count = map.get(t);
        map.remove(t);
        return count;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap source) {
        Map<T, Integer> temp =  source.toMap();
        for (Map.Entry<T, Integer> entry : temp.entrySet()){
            if (map.containsKey(entry.getKey())){
                map.put(entry.getKey(), map.get(entry.getKey())+entry.getValue());
            }else
                map.put(entry.getKey(), entry.getValue());
        }

        System.out.println(temp.size());

    }

    @Override
    public Map toMap() {
        return map;
    }

    @Override
    public void toMap(Map destination) {
        destination = (Map) this;

    }
}
