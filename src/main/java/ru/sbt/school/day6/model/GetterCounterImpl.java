package ru.sbt.school.day6.model;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GetterCounterImpl implements GetterCounter {
    public GetterCounterImpl() {
        super();
    }

    @Override
    public  int calcGetterCount(Class<?> clazz) {
        int count = 0;
        Method [] methods = clazz.getDeclaredMethods();
        for (Method m : methods){
            if (!m.getReturnType().getName().equals("void") && m.getModifiers() == Modifier.PUBLIC && (m.getName().contains("get") || m.getName().contains("is")))
                if (!m.isAnnotationPresent(Skip.class))
                    count++;
        }
        return count;
    }
}
