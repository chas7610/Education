package ru.sbt.school.day5.model;

public class Person {
    private String name;
    private long accountId;
    private int pin;
    private int money;

    public Person(){}

    public Person(String name, long accountId, int pin) {
        this.name = name;
        this.accountId = accountId;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}


