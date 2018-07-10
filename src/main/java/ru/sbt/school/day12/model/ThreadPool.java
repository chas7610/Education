package ru.sbt.school.day12.model;

public interface ThreadPool {
    void start();
    void execute(Runnable runnable);
}
