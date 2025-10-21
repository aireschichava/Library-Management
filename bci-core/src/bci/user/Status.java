package bci.user;
import java.io.Serializable;

public abstract class Status implements Serializable {
    @java.io.Serial
    private static final long serialVersionUID = 202507171004L;
    public abstract String getName();
    public abstract boolean canBorrow();
    
    @Override
    public String toString() {
        return getName();  
    }
}
