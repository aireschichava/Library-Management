package bci.app.user;

import bci.LibraryManager;
import bci.app.exceptions.UserIsActiveException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * 4.2.5. Settle a fine.
 * 
 * exception UserIsActiveException is 
 * 
 * 
 */
class DoPayFine extends Command<LibraryManager> {

    DoPayFine(LibraryManager receiver) {
        super(Label.PAY_FINE, receiver);
     
        addIntegerField("userId", Prompt.userId());

    }

    @Override
    protected final void execute() throws CommandException {

        int userId=integerField("userId");
        boolean success = _receiver.payFine(_receiver.getUser(userId), 5);

        if (!success) {
            throw new UserIsActiveException(userId);
        }


    }

}
