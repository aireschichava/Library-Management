package bci.user;

public class Compliant extends Behavior {
    private static final long serialVersionUID = 1L;

	@Override   
    public String getName() {
        return "CUMPRIDOR";
    }

    @Override
    public int maxConcurrentLoans() {
        return 5;
    }

    @Override
    public int loanPeriodForCopies(int totalCopies) {
        if (totalCopies == 1) return 8;
        if (totalCopies <= 5) return 15;
        return 30;
    }

    @Override
    public boolean canBorrowPrice(int price) {
        return true;
    }
}
