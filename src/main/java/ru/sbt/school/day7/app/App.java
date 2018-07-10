package ru.sbt.school.day7.app;

import ru.sbt.school.day7.api.Calculator;

public class App {
    Calculator calculator;

    public App(Calculator calculator) {
        this.calculator = calculator;
    }

    public Calculator getCalculator() {
        return calculator;
    }
}
