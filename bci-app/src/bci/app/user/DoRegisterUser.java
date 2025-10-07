package bci.app.user;

import bci.LibraryManager;
import bci.app.exceptions.UserRegistrationFailedException;
import bci.user.User;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
//FIXME maybe import classes

/**
 * 4.2.1. Register new user.
 */
class DoRegisterUser extends Command<LibraryManager> {

    DoRegisterUser(LibraryManager receiver) {
        super(Label.REGISTER_USER, receiver);
        //FIXME maybe define fields
    }

    @Override
    protected final void execute() throws CommandException { 
            //lançar a excepção
            String name = Form.requestString(Prompt.userName());
            String email = Form.requestString(Prompt.userEMail());
            User user = _receiver.registerUser(name, email);
            _display.popup(Message.registrationSuccessful(user.getId()));
    }

}
