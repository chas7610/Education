package ru.sbt.school.day5.model;

public class InsufficientFundsOnAccountException extends Exception{
    public InsufficientFundsOnAccountException(String message) {
        super(message);
    }
}
