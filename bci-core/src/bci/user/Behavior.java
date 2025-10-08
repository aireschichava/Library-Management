package bci.user;

import java.io.Serializable;

public abstract class Behavior implements Serializable {

    public abstract String getName();
    public abstract int maxConcurrentLoans();
    public abstract int loanPeriodForCopies(int totalCopies);
    public abstract boolean canBorrowPrice(int price);

    @Override
    public String toString() {
        return getName();
    }
}
