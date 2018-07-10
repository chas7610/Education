package ru.sbt.school.day8.impl;

import ru.sbt.school.day8.model.ServiceList;

import java.io.Serializable;
import java.util.*;

/**
 * Created by chernyshas on 07.06.2018.
 */
public class ServiceListImpl implements ServiceList {

    public static final long serialVersionUID = 3l;
    private String item;
    private double value;
    private Date date;

    public ServiceListImpl() {
    }

    public ServiceListImpl(String item, double value, Date date) {
        this.item = item;
        this.value = value;
        this.date = date;
    }

    @Override
    public List<String> run(String item, double value, Date date) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < value; i++){
            list.add(item + " " + value + " " + date);
        }
        return list;
    }

    @Override
    public List<String> work(String item) {
        List<String> list = new ArrayList<>();
        list.add(item);
        return list;
    }
    private Object writeReplace(){
        return new SerializationProxyClass(this);
    }

    private static class SerializationProxyClass implements Serializable{
        public static final long serialVersionUID = 2l;
        private String item;
        private double value;
        private Date date;

        public SerializationProxyClass(ServiceListImpl impl) {
            this.item = impl.item;
            this.value = impl.value;
            this.date = impl.date;
        }

        private Object readResolve() {
            return new ServiceListImpl(item, value, date);
        }
    }
}


