package ru.sbt.school.day8.impl;

import ru.sbt.school.day8.annotation.Cache;
import ru.sbt.school.day8.model.Type;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chernyshas on 06.06.2018.
 */
public class CacheProxy implements InvocationHandler{
    private Map<String, Double> cacheMap = new HashMap<>();
    private Map<String, List<String>> m1 = new HashMap<>();
    Object delegate;
    private static String path;

    public static <T> T cache(Object delegate, String path){
        CacheProxy.path = path;
        return  (T) Proxy.newProxyInstance(
                delegate.getClass().getClassLoader(),
                delegate.getClass().getInterfaces(),
                new CacheProxy(delegate));
    }

    public CacheProxy(Object delegate){
        this.delegate = delegate;
    }

    /**
     * Здесь мы можем перехватить вызов метода проксируемого интерфейса
     * Подменить или дополнить поведение этого метода
     * или делигировать вызов
     *
     * @param proxy - Объект метод которого был вызван
     * @param method Метод который был вызван
     * @param args Параметры метода
     * @return Результат работы метода
     * @throws Throwable Исключение которое может быть выбрашено
     */

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getReturnType().getName().equals("double")){
            return invokeDouble(method,args);
        }else if (method.getReturnType().getName().equals("java.util.List")){
            return invokeList(method,args);
        }
        return null;
    }

    private Object invokeDouble(Method method, Object[] args) throws Throwable{
        if (cacheMap.containsKey(args[0]))
            return cacheMap.get((String)args[0]);
        if (!method.isAnnotationPresent(Cache.class)){
            return method.invoke(delegate, args);
        }
        Cache ch = method.getAnnotation(Cache.class);
        if (ch.cacheType() == Type.IN_MEMORY){
            double result = (double)method.invoke(delegate, args);
            cacheMap.put((String) args[0], result);
            return result;
        }
        if (ch.cacheType() == Type.FILE){
            File file = new File(CacheProxy.path + ch.fileNamePrefix());
            if (file.exists()){
                ServiceImpl sImpl = Util.deSerializObject(CacheProxy.path + ch.fileNamePrefix());
                if (sImpl.getItem().equals(args[0]))
                    return sImpl.getValue();
            }
            double result = (double)method.invoke(delegate, args);
            Util.serializObject(delegate,CacheProxy.path + ch.fileNamePrefix());
            return result;
        }
        return null;
    }
    private Object invokeList(Method method, Object[] args) throws Throwable{
        return null;
    }
}

