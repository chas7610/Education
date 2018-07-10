package ru.sbt.school.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution2056 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            StringBuilder sb = new StringBuilder();
            int max = 0;
            do {
                String line = reader.readLine().toLowerCase();
                sb.append(line + " ");
            }while (reader.ready());
            Map<String, Integer>  wordsMap = new TreeMap<>();
            String [] words = sb.toString().split(" ");
            for (int i =0; i < words.length; i++){
                if (words[i].length() == 0)
                    continue;
                if (wordsMap.containsKey(words[i])) {
                    wordsMap.put(words[i], wordsMap.get(words[i]).intValue() + 1);
                    if (wordsMap.get(words[i]).intValue() > max)
                        max = wordsMap.get(words[i]).intValue();
                }else
                    wordsMap.put(words[i], 1);
            }
            for (Map.Entry<String, Integer> entry: wordsMap.entrySet()){
                if (entry.getValue() == max)
                    System.out.println(entry.getKey());
            }
        }catch (IOException ex){ex.printStackTrace();}
    }
}