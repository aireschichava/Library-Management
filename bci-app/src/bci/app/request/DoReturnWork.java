package bci.app.request;

import bci.LibraryManager;
import bci.user.User;
import bci.works.Work;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import bci.app.exceptions.NoSuchUserException;
import bci.app.exceptions.NoSuchWorkException;

//FIXME maybe import classes

/**
 * 4.4.2. Return a work.
 */
class DoReturnWork extends Command<LibraryManager> {

    DoReturnWork(LibraryManager receiver) {
        super(Label.RETURN_WORK, receiver);
        
    }

    @Override
    protected final void execute() throws CommandException {
        
    }

}
