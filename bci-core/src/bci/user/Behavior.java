package bci.user;

public abstract class Behavior {
    public abstract String getName();

    @Override
    public String toString() {
        return getName();
    }
}
