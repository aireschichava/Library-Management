package bci.rules;

import bci.user.User;
import bci.works.Work;

/**
 * Strategy interface for request rules.
 */
public interface RequestRule {
    /**
     * Returns true if the rule passes (request is allowed), false otherwise.
     */
    boolean validate(User user, Work work);
    int getId();
}
