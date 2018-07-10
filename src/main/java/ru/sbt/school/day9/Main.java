package ru.sbt.school.day9;

import ru.sbt.school.day9.model.Person;
import ru.sbt.school.day9.model.Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<Person> l = new ArrayList<>();
        l.add(new Person("Alex", 37));
        l.add(new Person("Gena", 51));
        l.add(new Person("Vitalik", 28));

        Map<String, Person> map = Streams.of(l)
                .filter(p -> p.getAge() < 40)
                .transform(p -> new Person(p.getName(), p.getAge() + 10))
                .toMap(p -> p.getName(), p ->p);
        for (Map.Entry entry : map.entrySet()){
            System.out.println(entry.getKey() + " " + ((Person)entry.getValue()).getAge());

        }

    }
}
