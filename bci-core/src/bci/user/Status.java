package bci.user;

public abstract class Status {
    
    public abstract String getName();
    public abstract boolean canBorrow();
    
    @Override
    public String toString() {
        return getName();  
    }
}
