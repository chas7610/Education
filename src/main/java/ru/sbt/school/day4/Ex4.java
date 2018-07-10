package ru.sbt.school.day4;

import ru.sbt.school.day4.ex4.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ex4 {
    public static void main(String[] args) {
        List<Integer> list = CollectionUtils.newArrayList();
        list.add(10);
        list.add(-1);
        list.add(7);
        list.add(3);
        list.add(-7);
        list.add(2);
        List<Integer> list2 = CollectionUtils.newArrayList();
        list2.add(7);
        list2.add(0);
        list2.add(-7);
        list2.add(1);
        CollectionUtils.addAll(list2, list);
        System.out.println(CollectionUtils.containsAny(list, list2));

        List<Integer> l = CollectionUtils.range(list, -1, 5);
        System.out.println(l);
    }
}
