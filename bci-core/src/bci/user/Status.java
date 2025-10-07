package bci.user;

public abstract class Status {
    
    public abstract String getName();
    
    @Override
    public String toString() {
        return getName();  
    }
}
