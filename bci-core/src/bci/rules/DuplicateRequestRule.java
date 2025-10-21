package bci.rules;

import bci.user.User;
import bci.works.Work;

/**
 * Rule 1: User cannot request the same work twice simultaneously.
 */
public class DuplicateRequestRule implements RequestRule {
	
	private int id=1;
	
	
    @Override
    public boolean validate(User user, Work work) {
		// Only consider active (not returned) loans when checking for duplicates
		return user.getLoans().stream().noneMatch(loan -> loan.getWorkId() == work.getId() && loan.isActive());
    }

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}

