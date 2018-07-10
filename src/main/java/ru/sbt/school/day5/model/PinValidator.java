package ru.sbt.school.day5.model;

public class PinValidator {

    public boolean checkPin(long id, int pin, TerminalServerImpl server) throws IncorrectPinException, AccountIsLockedException{
        if (server.checkPin(id, pin))
            return true;
        throw new IncorrectPinException("Неправильно введен Pin");
    }
}


