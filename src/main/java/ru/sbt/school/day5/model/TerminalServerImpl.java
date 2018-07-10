package ru.sbt.school.day5.model;

import java.util.Map;
import java.util.TreeMap;

public class TerminalServerImpl implements TerminalServer{
    private Map<Long, Account> accounts = new TreeMap<>();
    {
        accounts.put(1l,new Account(1l, 1234, 2000));
        accounts.put(2l,new Account(2l, 1111, 1000));
        accounts.put(3l,new Account(3l, 2222, 1000));
        accounts.put(4l,new Account(4l, 3333, 1000));
    }

    public void unLockAccount(long accountId){
        Account a = accounts.get(accountId);
        a.unLock();
    }

    public boolean checkAccount(long accountId) throws AccountIsLockedException {
        if(accounts.containsKey(accountId)){
            Account a = accounts.get(accountId);
            if (!a.isLock())
                return true;
        }
        return false;
    }

    protected boolean checkPin(long accountId, int pin) throws AccountIsLockedException{
        Account a = accounts.get(accountId);

        if (a.checkPin(pin))
            return true;
        return false;
    }

    public boolean putToAccoun(long accountId, int m) throws Multiplicity100Exception{
        if (m % 100 != 0)
            throw new Multiplicity100Exception("Сумма должна быть кратна 100");
        Account a = accounts.get(accountId);
        a.replenish(m);
        return true;
    }

    public boolean pullFromAccount(long accountId, int m) throws InsufficientFundsOnAccountException, Multiplicity100Exception {
        if (m % 100 != 0)
            throw new Multiplicity100Exception("Сумма должна быть кратна 100");
        Account a = accounts.get(accountId);
        if (a.withdraw(m))
            return true;
        return false;
    }
    public int checkBalance(long accountId){
        Account a = accounts.get(accountId);
        return a.getMoney();
    }
}


