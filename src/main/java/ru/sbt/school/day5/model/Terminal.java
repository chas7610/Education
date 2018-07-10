package ru.sbt.school.day5.model;

public interface Terminal {
    void checkAccount(long accountId);
    boolean putToAccoun(long accountId, int m);
    boolean pullFromAccount(long accountId, int m);
    boolean connectToServer(TerminalServer server);
    boolean checkBalance(long accountId);
}
