package bci.user;

import java.io.Serializable;

public class Active extends Status implements Serializable {
    @Override
    public String getName() {
        return "ACTIVO";
    }
    @Override
    public boolean canBorrow() {
        return true;
    }

}
