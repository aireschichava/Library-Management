package bci.app.request;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import bci.app.exceptions.NoSuchUserException;
import bci.app.exceptions.NoSuchWorkException;
import bci.app.exceptions.WorkNotBorrowedByUserException;
import bci.user.User;
import bci.ReturnResult;
import bci.works.Loan;
import bci.works.Work;

//FIXME maybe import classes

/**
 * 4.4.2. Return a work.
 */
class DoReturnWork extends Command<LibraryManager> {

    DoReturnWork(LibraryManager receiver) {
        super(Label.RETURN_WORK, receiver);
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
			throw new bci.app.exceptions.NoSuchWorkException(workId);
        }  
        Loan loan = _receiver.findActiveLoan(userId, workId);
        if (loan == null) {
            throw new WorkNotBorrowedByUserException(workId, userId);
        }

        // delegate domain handling to LibraryManager and present results
        ReturnResult result = _receiver.returnWork(userId, workId);
        if (!result.isSuccess()) {
            throw new WorkNotBorrowedByUserException(workId, userId);
        }

        if (result.getFine() > 0) {
            _display.popup(Message.showFine(userId, result.getFine()));
            boolean wantsToPay = Form.confirm(Prompt.finePaymentChoice());
            if (wantsToPay) {
                _receiver.payFine(user);
            }
        }
    }
}