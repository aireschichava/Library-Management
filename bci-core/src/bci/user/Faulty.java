package bci.user;

public class Faulty extends Behavior {
    private static final long serialVersionUID = 1L;

	@Override
    public String getName() {
        return "FALTOSO";
    }

    @Override
    public int maxConcurrentLoans() {
        return 1;
    }

    @Override
    public int loanPeriodForCopies(int totalCopies) {
        return 2;
    }

    @Override
    public boolean canBorrowPrice(int price) {
        return price <= 25;
    }
}
