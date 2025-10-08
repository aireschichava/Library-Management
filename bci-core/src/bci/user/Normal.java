package bci.user;

public class Normal extends Behavior {
    @Override
    public String getName() {
        return "NORMAL";
    }

    @Override
    public int maxConcurrentLoans() {
        return 3;
    }

    @Override
    public int loanPeriodForCopies(int totalCopies) {
        if (totalCopies == 1) return 3;
        if (totalCopies <= 5) return 8;
        return 15;
    }

    @Override
    public boolean canBorrowPrice(int price) {
        return price <= 25;
    }
}
