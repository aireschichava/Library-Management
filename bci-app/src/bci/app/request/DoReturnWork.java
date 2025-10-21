package bci.app.request;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import bci.app.exceptions.NoSuchUserException;
import bci.app.exceptions.NoSuchWorkException;
import bci.app.exceptions.WorkNotBorrowedByUserException;
import bci.user.User;
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

        int copiesBeforeReturn = work.getAvailableCopies();
        int currentDate = _receiver.getCurrentDate();
        loan.returnWork(currentDate);

        int fine = 0;
        if (!loan.isReturnedOnTime()) {
            int daysLate = loan.getDaysLate(currentDate);
            fine = daysLate * 5;
            user.addFine(fine);
            user.setSuspended();

            _display.popup(Message.showFine(userId, fine));
            addBooleanField("payFine", Prompt.finePaymentChoice()); // "(s/n)"
            boolean wantsToPay = booleanField("payFine");

            if (wantsToPay) {
                _receiver.payFine(user);
            }
        }
        if (copiesBeforeReturn == 0 && work.getAvailableCopies() > 0) {
            work.notifyObservers(work.message());
        }
    }
}