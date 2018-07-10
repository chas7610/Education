package ru.sbt.school.day6;

import ru.sbt.school.day6.Proxy.GetterCounterProxy;
import ru.sbt.school.day6.model.GetterCounter;
import ru.sbt.school.day6.model.GetterCounterImpl;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        GetterCounter getterCounter = new GetterCounterImpl();
        Person person = new Person();
        System.out.println("Class: " + person.getClass().getName() + " - getter:" + getterCounter.calcGetterCount(person.getClass()));

        System.out.println();

        GetterCounter proxy = (GetterCounter) Proxy.newProxyInstance(
                GetterCounterImpl.class.getClassLoader(),
                GetterCounterImpl.class.getInterfaces(),
                new GetterCounterProxy(new GetterCounterImpl()));
        String s = "";
        System.out.println("Class: " + s.getClass().getName() + " - getter:" + proxy.calcGetterCount(s.getClass()));
        System.out.println("Class: " + person.getClass().getName() + " - getter:" + getterCounter.calcGetterCount(person.getClass()));
        Integer i = new Integer(1);
        System.out.println("Class: " + i.getClass().getName() + " - getter:" +proxy.calcGetterCount(i.getClass()));
        System.out.println("Class: " + person.getClass().getName() + " - getter:" + getterCounter.calcGetterCount(person.getClass()));
    }
}
