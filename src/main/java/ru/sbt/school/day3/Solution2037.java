package ru.sbt.school.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2037 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String [] words = reader.readLine().split("\\,");
            int k = Integer.parseInt(reader.readLine());
            StringBuilder sb = new StringBuilder();
            boolean firstComa = true;
            for (int i = 0; i < words.length; i ++){
                if (words[i].length()>=k) {
                    if (!firstComa)
                        System.out.print(",");
                    System.out.print(words[i]);
                    firstComa = false;
                }
            }
        }catch (IOException ex){ex.printStackTrace();}
    }
}
