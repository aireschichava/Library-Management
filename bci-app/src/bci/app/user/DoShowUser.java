package bci.app.user;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
//FIXME maybe import classes

/**
 * 4.2.2. Show specific user.
 */
class DoShowUser extends Command<LibraryManager> {

    DoShowUser(LibraryManager receiver) {
        super(Label.SHOW_USER, receiver);
      
    }

    @Override
    protected final void execute() throws CommandException {
        int id = Form.requestInteger(Prompt.userId());
        String userInfo = _receiver.showUser(id);
        if (userInfo == null) {
            throw new bci.app.exceptions.NoSuchUserException(id);
        }
        _display.popup(userInfo);
    }

}
