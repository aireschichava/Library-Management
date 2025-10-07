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
        _display.popup(Message.currentDate(_receiver.getCurrentDate()));
    }

}
