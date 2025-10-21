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
     * Add a rules to the composite.
     * @param rule
     */

     //add multiple rules at once using a list
    public void addRules(List<RequestRule> rule) {
        rules.addAll(rule);
    }

    //add a single rule
    public void addRules(RequestRule rule) {
        rules.add(rule);
    }

    //add multiple rules at once using varargs
    public void addRules(RequestRule... ruleArray) {
         for (RequestRule rule : ruleArray) {
                 rules.add(rule);
        }
    }

    /**
     * Validate all rules in the composite, one by one. If any rule fails, the entire validation fails.
     * @param user The user making the request.
     * @param work The work being requested.
     * @return true if all rules pass, false if any rule fails.
     */
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
