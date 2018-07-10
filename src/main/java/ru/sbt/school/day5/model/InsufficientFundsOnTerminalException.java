package ru.sbt.school.day5.model;

public class InsufficientFundsOnTerminalException extends Exception {
    public InsufficientFundsOnTerminalException(String message) {
        super(message);
    }
}
