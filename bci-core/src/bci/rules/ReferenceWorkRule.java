package bci.rules;

import bci.user.User;
import bci.works.Category;
import bci.works.Work;

/**
 * Rule 5: User cannot request reference works.
 */

public class ReferenceWorkRule implements RequestRule {
	
	
	private int id=5;
    @Override
    public boolean validate(User user, Work work) {
      
        return work.getCategory()==Category.REFERENCE;
    }

    @Override
    public int getId() {
        return id;
    }
}