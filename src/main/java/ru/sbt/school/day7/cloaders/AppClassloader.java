package ru.sbt.school.day7.cloaders;

public class AppClassloader extends ApiClassloader {
    public AppClassloader(ClassLoader parent, String path) {
        super(parent, path);
    }
}
