package ru.sbt.school.day12.impl;
import  ru.sbt.school.day12.model.ThreadPool;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chernyshas on 20.06.2018.
 */
public class FixedThreadPool implements ThreadPool {

    private int thread;
    private int countActiveThread;
    private int countTask;
    private final ArrayList<Thread> pool;


    private FixedThreadPool(int thread) {
        this.thread = thread;
        countActiveThread = 0;
        countTask = 0;
        pool = new ArrayList<>();
    }

    public static ThreadPool newFixedThreadPool(int thread){
        return new FixedThreadPool(thread);
    }

    private boolean isRunTask(Runnable task){
        List list = Thread.getAllStackTraces().entrySet().stream().filter(th -> th.getKey().getName().contains("myThread")).collect(Collectors.toList());
        for (Iterator it = list.iterator(); it.hasNext();){
            Map.Entry<Thread, StackTraceElement> entry = (Map.Entry<Thread, StackTraceElement>)it.next();
            if (task.equals(entry.getKey()))
                return true;
        }
        return false;
    }

    @Override
    public void start() {
        synchronized (this) {
            for (Thread thread : pool) {
                if (!isRunTask(thread)) {
                    thread.start();
                    countActiveThread++;
                }
            }
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (runnable == null)
            throw new NullPointerException();
        if (countActiveThread == thread)
            clearThreadPool();
        if (pool.size()<thread){
            pool.add(new Thread(runnable, "myThread_" + runnable.hashCode()));
            countTask++;
            start();
        }
    }

    private void clearThreadPool() {
        for (;;){
            List<Thread> list = Thread.getAllStackTraces().entrySet().stream()
                    .filter(entry -> entry.getKey().getName().contains("myThread"))
                    .flatMap(entry -> Stream.of(entry.getKey()))
                    .collect(Collectors.toList());
            if (list.size() == 0)
                break;
            for (Iterator<Thread> it = list.iterator(); it.hasNext(); ) {
                Thread task = it.next();
                if (!task.isAlive()) {
                    int index = pool.indexOf(task);
                    pool.remove(index);
                    countTask--;
                    countActiveThread--;
                }
            }
        }
    }

}
