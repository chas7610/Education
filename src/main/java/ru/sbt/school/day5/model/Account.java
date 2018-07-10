package ru.sbt.school.day5.model;

public class Account {
    private long id;
    private int pin;
    private int money;
    private boolean lock;
    private int attempt;

    public Account(long id, int pin, int money) {
        this.id = id;
        this.pin = pin;
        this.money = money;
    }

    public void setAttempt() throws AccountIsLockedException{
        attempt++;
        if (attempt == 3){
            lock = true;
            throw new AccountIsLockedException("Карта заблокирована, попробуйте через 5 сек");
        }
    }

    public boolean isLock() {
        return lock;
    }
    public void unLock(){
        lock = false;
    }
    public boolean withdraw(int m) throws InsufficientFundsOnAccountException {
        if (money - m >= 0) {
            money -= m;
            return true;
        }
        else
            throw new InsufficientFundsOnAccountException("Недостаточно средств на счете");

    }
    public boolean checkPin(int pin) throws AccountIsLockedException{
        if (this.pin != pin){
            setAttempt();
            return false;
        }
        else
            return true;
    }
    public void replenish(int m){
        money +=m;
    }

    public int getMoney() {
        return money;
    }

}