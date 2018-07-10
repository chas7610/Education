package ru.sbt.school.day5.model;

public class TerminalImpl implements Terminal {
    private int moneyInTerminal;
    private PinValidator pinValidator;
    private TerminalServerImpl server;
    private long accountId;
    private int pin;

    private   TerminalImpl(int moneyInTerminal) {
        server = new TerminalServerImpl();
        pinValidator = new PinValidator();
        this.moneyInTerminal = moneyInTerminal;
    }

    public static TerminalImpl initTerminal(int money){
        return new TerminalImpl(money);
    }

    @Override
    public void checkAccount(long accountId) {
        try {
            server.checkAccount(accountId);
        }catch (AccountIsLockedException ex){
            System.out.println(ex.getMessage());
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){e.printStackTrace();}
            server.unLockAccount(accountId);
        }
    }

    public boolean validatePin(long accountId, int pin){
        checkAccount(accountId);
        try {
            pinValidator.checkPin(accountId, pin, server);
            return true;
        }catch (IncorrectPinException ex){
            System.out.println(ex.getMessage());
        }catch (AccountIsLockedException ex){
            System.out.println(ex.getMessage());
            try {
                Thread.sleep(5000);
            }catch (InterruptedException e){e.printStackTrace();}
            server.unLockAccount(accountId);
        }
        return false;
    }

    @Override
    public boolean putToAccoun(long accountId, int m){
        try {
            if(server.putToAccoun(accountId, m)){
                moneyInTerminal += m;
                return true;
            }
        } catch (Multiplicity100Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean pullFromAccount(long accountId, int m) {
        try {
            server.pullFromAccount(accountId, m);
            if (moneyInTerminal - m <0){
                server.putToAccoun(accountId,m);
                throw new InsufficientFundsOnTerminalException("Недостаточно средств в терминале");
            }
            moneyInTerminal -= m;
            return true;
        } catch (InsufficientFundsOnAccountException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientFundsOnTerminalException e){
            System.out.println(e.getMessage());
        } catch (Multiplicity100Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean checkBalance(long accountId){
        System.out.println(server.checkBalance(accountId));
        return true;
    }

    @Override
    public boolean connectToServer(TerminalServer server) {
        return false;
    }
}
