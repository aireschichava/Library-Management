package bci.app.user;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchUserException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * 4.2.3. Show notifications of a specific user.
 */
class DoShowUserNotifications extends Command<LibraryManager> {

    DoShowUserNotifications(LibraryManager receiver) {
        super(Label.SHOW_USER_NOTIFICATIONS, receiver);
        addIntegerField("userId", Prompt.userId());
    }

    @Override
    protected final void execute() throws CommandException {
        int userId = integerField("userId");

        if (_receiver.getUser(userId) == null) {
                throw new NoSuchUserException(userId);
            }
        _display.popup(_receiver.getUserNotifications(userId));
    }

}
