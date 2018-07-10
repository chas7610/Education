package ru.sbt.school.day4;

import ru.sbt.school.day4.ex3.CountMap;
import ru.sbt.school.day4.ex3.CountMapIml;

import java.util.Iterator;
import java.util.Map;


public class Ex3 {

    public static void main(String[] args) {
        CountMap<Integer> countMap = new CountMapIml<>();
        countMap.add(10);
        countMap.add(10);
        countMap.add(5);
        countMap.add(6);
        countMap.add(5);
        countMap.add(10);
        System.out.println("countMap.getCount(10) = " + countMap.getCount(10));
        System.out.println("countMap.getCount(5) = " + countMap.getCount(5));
        System.out.println("countMap.getCount(6) = " + countMap.getCount(6));
        System.out.println("countMap.size = " + countMap.size());

        Map<Integer, Integer> map = countMap.toMap();
        System.out.println("======================");
        for (Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator(); it.hasNext();){
            Map.Entry<Integer, Integer> entry = it.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        CountMap<Integer> countMap1 = new CountMapIml<>();
        countMap1.add(10);
        countMap1.add(10);
        countMap1.add(11);
        countMap1.add(12);
        countMap1.add(13);
        countMap1.add(12);

        countMap.addAll(countMap1);
        System.out.println("======================");
        System.out.println("countMap.size = " + countMap.size());
        System.out.println("countMap.getCount(10) = " + countMap.getCount(10));
        System.out.println("countMap.getCount(11) = " + countMap.getCount(11));
        System.out.println("countMap.getCount(12) = " + countMap.getCount(12));
        System.out.println("countMap.getCount(13) = " + countMap.getCount(13));

        System.out.println("======================");
        CountMap<String> mapS = new CountMapIml<>();
        mapS.add("test");
        mapS.add("generic");
        mapS.add("String");
        mapS.add("!");
        mapS.add("test");
        mapS.add("generic");
        System.out.println("maps.getCount(\"test\") = " + mapS.getCount("test"));
        System.out.println("mapS.getCount(\"generic\") = " + mapS.getCount("generic"));
        System.out.println("mapS.getCount(\"String\") = " + mapS.getCount("String"));
        System.out.println("mapS.getCount(\"!\") = " + mapS.getCount("!"));
        System.out.println("mapS.size = " + mapS.size());

    }

}


