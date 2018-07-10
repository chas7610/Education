package ru.sbt.school.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2057 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int n = Integer.parseInt(reader.readLine());
            StringBuilder sb = new StringBuilder();
            Map<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < n; i++){
                int [] description = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                if (description[0] == 1){
                    if (map.containsKey(description[1]))
                        map.put(description[1], map.get(description[1]).intValue()+1);
                    else
                        map.put(description[1],1);
                }else if (description[0] ==2){
                    int key =  map.keySet().iterator().next();
                    sb.append(key + System.getProperty("line.separator"));
                    int value = map.get(key);
                    if (value -1 >= 1)
                        map.put(key,--value);
                    else
                        map.remove(key);
                }
            }
            System.out.println(sb.toString());
        }catch (IOException ex){ex.printStackTrace();}
    }
}