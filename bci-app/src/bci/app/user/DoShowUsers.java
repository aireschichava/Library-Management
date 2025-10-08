package bci.app.user;

import bci.LibraryManager;
import bci.user.User;
import pt.tecnico.uilib.menus.Command;
//FIXME maybe import classes

/**
 * 4.2.4. Show all users.
 */
class DoShowUsers extends Command<LibraryManager> {

    DoShowUsers(LibraryManager receiver) {
        super(Label.SHOW_USERS, receiver);
	//FIXME maybe define fields
    }

    @Override
    protected final void execute() {
        java.util.List<User> users = _receiver.getAllUsers();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u == null) continue;
            sb.append(u.toDisplayString());
            if (i < users.size() - 1) sb.append("\n");
        }
        if (sb.length() > 0) {
            _display.popup(sb.toString());
        }
    }

}
