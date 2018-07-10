package ru.sbt.school.day8.impl;

import ru.sbt.school.day8.model.Service;

import java.io.Serializable;

/**
 * Created by chernyshas on 05.06.2018.
 */
public class ServiceImpl implements Service, Serializable{

    private static class SerializationProxyClass implements Serializable{
        public static final long serialVersionUID = 2l;
        private String item;
        private double value;
        public SerializationProxyClass(ServiceImpl impl) {
            this.item = impl.item;
            this.value = impl.value;
        }
        private Object readResolve() {
            return new ServiceImpl(item, value);
        }
    }

    public static final long serialVersionUID = 1l;
    private String item;
    private double value;

    public ServiceImpl() {
    }

    public ServiceImpl(String item, double value) {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public double doHardWork(String str, int i) {
        double result = 7.5;
        for (int count = 0; i < i; count++){
            result +=count;
        }
        System.out.println(result);
        item = str;
        value = i;
        return result;
    }
    private Object writeReplace(){
        return new SerializationProxyClass(this);
    }
}


