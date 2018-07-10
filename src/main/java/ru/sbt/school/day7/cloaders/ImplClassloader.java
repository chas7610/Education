package ru.sbt.school.day7.cloaders;

public class ImplClassloader extends ApiClassloader {
    public ImplClassloader(ClassLoader parent, String path) {
        super(parent, path);
    }
}
