package ru.sbt.school.day5.model;

public class IncorrectPinException extends Exception {
    public IncorrectPinException(String message) {
        super(message);
    }
}
