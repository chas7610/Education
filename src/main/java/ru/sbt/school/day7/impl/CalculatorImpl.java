package ru.sbt.school.day7.impl;

import ru.sbt.school.day7.api.Calculator;

public class CalculatorImpl implements Calculator {

    @Override
    public int plus(int a, int b) {
        return a + b;
    }
}
