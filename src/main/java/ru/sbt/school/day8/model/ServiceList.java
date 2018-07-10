package ru.sbt.school.day8.model;

import ru.sbt.school.day8.annotation.Cache;

import java.util.Date;
import java.util.List;

import static ru.sbt.school.day8.model.Type.FILE;
import static ru.sbt.school.day8.model.Type.IN_MEMORY;

/**
 * Created by chernyshas on 07.06.2018.
 */
public interface ServiceList {
    @Cache(cacheType = FILE, fileNamePrefix = "data.ser", zip = true)
    List<String> run(String item, double value, Date date);;
    @Cache(cacheType = IN_MEMORY, listList = 100_000)
    List<String> work(String item);
}
