package ru.sbt.school.day12.impl;
import  ru.sbt.school.day12.model.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chernyshas on 20.06.2018.
 */
public class ScalableThreadPool implements ThreadPool {
    private final int min;
    private final int max;
    private List<Thread> pool;
    private int countActiveThread;
    private int countTask;
    private int allThread;

    private ScalableThreadPool(int min, int max) {
        this.min = min;
        this.max = max;
        pool = new ArrayList<>();
        allThread = min;
    }

    public static ThreadPool newScalableThreadPool(int min, int max){
        return new ScalableThreadPool(min, max);
    }

    @Override
    public void start() {
//        synchronized (this)
        {
            for (Thread thread : pool) {
                if (!isRunTask(thread)) {
                    thread.start();
                    countActiveThread++;
                    if (countActiveThread == allThread)
                        allThread++;
                }
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null)
            throw new NullPointerException();
        if (countTask <= allThread){
            if (pool.add(new Thread(runnable, "my_Thread_" + runnable.hashCode()))){
                synchronized (this) {
                    start();
                    countTask++;
                }
            }
        }if (allThread == max){
            waitToEndTast();
        }

    }

    private void waitToEndTast() {
        List<Thread> list = Thread.getAllStackTraces().entrySet().stream()
                .filter(entry -> entry.getKey().getName().contains("my_Thread"))
                .flatMap(entry -> Stream.of(entry.getKey()))
                .collect(Collectors.toList());
        for (;;) {
            if (allThread < max)
                break;
            for (Thread task : list){
                if (!task.isAlive()){
                    synchronized (this){
                        pool.remove(task);
                        countActiveThread--;
                        countTask--;
                    }
                }
            }
            if (countTask<=min){
                allThread = min;
            }
        }
    }

    public boolean isRunTask(Thread thread) {
        List<Thread> list = Thread.getAllStackTraces().entrySet().stream()
                .filter(entry -> entry.getKey().getName().contains("my_Thread"))
                .flatMap(entry -> Stream.of(entry.getKey()))
                .collect(Collectors.toList());
        for (Thread t : list){
            if (t.equals(thread) && t.isAlive()==true)
                return true;
        }
        return false;
    }
}
