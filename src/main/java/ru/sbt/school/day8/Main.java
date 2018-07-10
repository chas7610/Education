package ru.sbt.school.day8;

import ru.sbt.school.day8.impl.ServiceImpl;
import ru.sbt.school.day8.impl.ServiceListImpl;
import ru.sbt.school.day8.impl.CacheProxy;
import ru.sbt.school.day8.model.Service;
import ru.sbt.school.day8.model.ServiceList;

import java.util.List;

/**
 * Created by chernyshas on 05.06.2018.
 */
public class Main {


    public static void main(String[] args) {

        String path = Main.class.getResource(".").getPath();

        Service proxy = CacheProxy.cache(new ServiceImpl(), path);

        System.out.println(proxy.doHardWork("Work1", 10));
        System.out.println(proxy.doHardWork("Work1", 5));


        ServiceList proxyList = CacheProxy.cache(new ServiceListImpl(), path);

        List<String> list = proxyList.work("Work");
        list = proxyList.work("Work1");

    }
}


