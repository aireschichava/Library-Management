package bci.app.main;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;

//FIXME maybe import classes

/**
 * 4.1.2. Display the current date.
 */
class DoDisplayDate extends Command<LibraryManager> {

    DoDisplayDate(LibraryManager receiver) {
        super(Label.DISPLAY_DATE, receiver);
        // no fields
    }

    @Override
    protected final void execute() {
<<<<<<< HEAD
        _display.popup(Message.currentDate(_receiver.getCurrentDate()));
=======
        //FIXME implement command
        int currentDate = _receiver.getCurrentDate();
        _display.popup(Message.currentDate(currentDate));
>>>>>>> 90dee6fa8b40c8f729c236159352f290a4893f73
    }

}
