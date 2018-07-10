package ru.sbt.school.day8.annotation;

import ru.sbt.school.day8.model.Type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by chernyshas on 05.06.2018.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {
    Type cacheType () default Type.FILE;
    String fileNamePrefix() default "temp.ser";
    boolean zip() default false;
    Class [] identityBy() default  {};
    int listList() default 1;
}


