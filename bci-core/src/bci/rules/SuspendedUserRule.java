package bci.rules;

import bci.user.User;
import bci.works.Work;

/**
 * Rule 2: User cannot request if suspended.
 * 
 @param user The user making the request.
 @param work The work being requested.
 @return true if the user is not suspended, false otherwise.
 */
public class SuspendedUserRule implements RequestRule {
	
	private int id=2;
        
    @Override
    public boolean validate(User user, Work work) {
        return !user.isSuspended();
      
    }

	@Override
	public int getId() {
		return id;
	}
}