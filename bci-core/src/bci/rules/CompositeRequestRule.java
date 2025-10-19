package bci.rules;

import bci.user.User;
import bci.works.Work;
import java.util.ArrayList;
import java.util.List;

/**
 * Composite pattern for combining multiple request rules.
 */
public class CompositeRequestRule implements RequestRule {
    private final List<RequestRule> rules = new ArrayList<>();
    private int id;


    /**
     * Add a rule to the composite.
     * @param rule
     */
    public void addRules(List<RequestRule> rule) {
        rules.addAll(rule);
    }


    public void addRules(RequestRule... ruleArray) {
         for (RequestRule rule : ruleArray) {
                 rules.add(rule);
        }
}




    @Override
    public boolean validate(User user, Work work) {
        for (RequestRule rule : rules) {
            if (!rule.validate(user, work)) {
            	id=rule.getId();
                return false;
            }
        }
        return true;
    }

	@Override
	public int getId() {

		return id;
	}
}
