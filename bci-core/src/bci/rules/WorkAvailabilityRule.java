package bci.rules;

import bci.user.User;
import bci.works.Work;

/**
 * Rule 3: Work must have available copies.
 */
public class WorkAvailabilityRule implements RequestRule {
	
	private int id=3;
    @Override
    public boolean validate(User user, Work work) {
        return work.getAvailableCopies() > 0;
    }

	@Override
	public int getId() {

		return id;
	}
}

/** Check if the work has available copies greater than zero */