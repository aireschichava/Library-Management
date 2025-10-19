package bci.rules;

import bci.user.User;
import bci.works.Work;

/**
 * Rule 6: User cannot request works over 25 euros (unless compliant).
 */
public class ExpensiveWorkRule implements RequestRule {
	
	private int id=6;
	
    @Override
    public boolean validate(User user, Work work) {
        return work.getPrice() <= 25.0 || user.getBehavior().getName().equals("CUMPRIDOR");
       
    }

	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}

}

/* Rule 6: User cannot request works over 25 euros (unless compliant). 
 * First, we check if the work's price is less than or equal to 25 euros.
 * If it is, the request is valid. If the price exceeds 25 euros, we then check the user's behavior.
 * If the user's behavior is "CUMPRIDOR" (compliant), the request is also valid.
 * Otherwise, the request is invalid.
*/