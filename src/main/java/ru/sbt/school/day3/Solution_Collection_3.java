package ru.sbt.school.day3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution_Collection_3 {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("/home/chas/IdeaProjects/Exc1/src/text.txt"));
            do {
                StringTokenizer st = new StringTokenizer(reader.readLine());
                for (int i = 0; i <st.countTokens(); i++){
                    String word = st.nextToken();
                    if (map.containsKey(word)){
                        map.put(word, map.get(word)+1);
                    }
                    else{
                        map.put(word, 1);
                    }
                }
            }while (reader.ready());
            System.out.println("1:");
            System.out.println(map.size());
            System.out.println("===================");
            System.out.println("2:");
            List<Map.Entry<String,Integer>> list = new ArrayList<>(map.entrySet());

            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                    return ((Integer)o1.getKey().length()).compareTo((Integer)o2.getKey().length());
//                    if (o1.getKey().length() < o2.getKey().length())
//                        return -1;
//                    if (o1.getKey().length() > o2.getKey().length())
//                        return 1;
//                    return 0;
                }
            });
            for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();)
            {
                System.out.println(it.next().getKey());
            }
            System.out.println("-------------------");
            for (Iterator it = map.entrySet().iterator(); it.hasNext();){
                Map.Entry<String, Integer> entry = (Map.Entry)it.next();
                System.out.println(entry.getKey());
            }
            System.out.println("===================");
            System.out.println("3:");
            for (Iterator it = map.entrySet().iterator(); it.hasNext();){
                Map.Entry<String, Integer> entry = (Map.Entry)it.next();
                System.out.println(entry.getKey() + "   " + entry.getValue());
            }
            System.out.println("===================");
            System.out.println("4:");
            list = new ArrayList<>(map.entrySet());
            Collections.reverse(list);
            for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();)
            {
                System.out.println(it.next().getKey());
            }
            System.out.println("===================");
            System.out.println("5:");

            Iterator<Map.Entry<String,Integer>> it = new Iterator<Map.Entry<String, Integer>>() {
                List list = new ArrayList<>(map.entrySet());
                int index = list.size()-1;
                @Override
                public boolean hasNext() {
                    if (index >= 0)
                        return true;
                    return false;
                }

                @Override
                public Map.Entry<String, Integer> next() {
                    Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>)list.get(index);
                    --index;
                    return entry;

                }
            };
            while (it.hasNext()){
                System.out.println(it.next().getKey());
            }

        }catch (FileNotFoundException ex){ex.printStackTrace();}
        catch (IOException ex){ex.printStackTrace();}

    }
}