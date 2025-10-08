package bci.user;

public class Suspended extends Status{
    @Override
    public String getName() {
        return "SUSPENSO";
    }
    @Override
    public boolean canBorrow() {
        return false;
    }
}
