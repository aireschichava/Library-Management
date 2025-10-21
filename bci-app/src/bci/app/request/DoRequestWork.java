package bci.app.request;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import bci.user.User;
import bci.works.Work;
import bci.app.exceptions.NoSuchUserException;
import bci.app.exceptions.NoSuchWorkException;

//FIXME maybe import classes

/**
 * 4.4.1. Request work.
 */
class DoRequestWork extends Command<LibraryManager> {

    DoRequestWork(LibraryManager receiver) {
        super(Label.REQUEST_WORK, receiver);
        
    }

    @Override
    protected final void execute() throws CommandException {
    
        
        
    }

}
