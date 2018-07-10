package ru.sbt.school.day6.Proxy;

import ru.sbt.school.day6.model.GetterCounter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class GetterCounterProxy implements InvocationHandler {

    private Map<Object, Integer> cache = new HashMap<>();

    private GetterCounter delegate;

    public GetterCounterProxy(GetterCounter delegate) {
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
        int result = 0;
        if (cache.containsKey(args[0]))
            result = cache.get(args[0]);
        else
        {
            result = (int)method.invoke(delegate,args);
            cache.put(args[0], result);
        }
        return result;
    }
}
