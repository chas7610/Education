package ru.sbt.school.day8.model;

import ru.sbt.school.day8.annotation.Cache;

import static ru.sbt.school.day8.model.Type.FILE;

/**
 * Created by chernyshas on 05.06.2018.
 */
public interface Service {
    @Cache(cacheType = FILE, fileNamePrefix = "data.ser", zip = true)
    double doHardWork(String str, int i);
}