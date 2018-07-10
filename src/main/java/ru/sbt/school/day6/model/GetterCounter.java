package ru.sbt.school.day6.model;

public interface GetterCounter {
    /**
     * Возвращает колличество геттеров в переданном класс
     * @param clazz класс в котором необходимо посчитать геттеры
     * @return возвращает количество найденных геттеров
     */
    int calcGetterCount(Class<?> clazz);
}
