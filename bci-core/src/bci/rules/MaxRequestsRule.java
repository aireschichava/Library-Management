package bci.rules;

import bci.user.*;
import bci.works.Work;

/**
 * Rule 4: User cannot exceed max allowed requests.
 */
public class MaxRequestsRule implements RequestRule {
	
	private int id=4;
	
    @Override
    public boolean validate(User user, Work work) {

        return user.getNumberOfLoans() < user.getBehavior().maxConcurrentLoans();
    }

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
   

}

/** Get the number of loans for a user and then get the number of max allowed loans */