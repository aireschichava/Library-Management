package bci.user;

public class Active extends Status {
    @Override
    public String getName() {
        return "ACTIVO";
    }
    @Override
    public boolean canBorrow() {
        return true;
    }

}
