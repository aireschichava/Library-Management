package bci.app.request;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import bci.app.exceptions.*;
import bci.user.User;
import bci.works.Work;
//FIXME maybe import classes

/**
 * 4.4.1. Request work.
 */
class DoRequestWork extends Command<LibraryManager> {

    DoRequestWork(LibraryManager receiver) {
        super(Label.REQUEST_WORK, receiver);
		 addIntegerField("User ID: ", bci.app.user.Prompt.userId());
         addIntegerField("Work ID: ", bci.app.work.Prompt.workId());
        
    }

    @Override
    protected final void execute() throws CommandException {
        int userId = integerField("User ID: ");
		int workId = integerField("Work ID: ");
		
		User user = _receiver.getUser(userId);
		if (user == null) {
		    throw new NoSuchUserException(userId);
		}
		Work work = _receiver.getWork(workId);
		if (work == null) {
			throw new NoSuchWorkException(workId);
		}
		
		
		//validate rules, returns rule id that failed or 0 if all passed
		int ruleId = _receiver.validateRules(user, work);
		if (ruleId!= 0 && ruleId !=3) {
			throw new  BorrowingRuleFailedException(userId,workId,ruleId);
	
		}
		//if rule 3 (notification preference) passed, ask user if they want to be notified
		//if yes add them as observer to the work
		if (ruleId == 3) {
			// Ask directly for confirmation instead of adding a boolean field at runtime
			boolean wantsNotification = Form.confirm(Prompt.returnNotificationPreference());
			if (wantsNotification) {
				_receiver.addObserver(user, work);
			}
			return;
		}
		
		int day=_receiver.loanWork(user, work, _receiver.getCurrentDate());
		_display.popup(Message.workReturnDay(workId, day));
    }

}
//we are calling are not importing direcrly because of name conflicts between the packages for Prompt and Message