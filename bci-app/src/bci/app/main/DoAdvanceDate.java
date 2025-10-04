package bci.app.main;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
//FIXME maybe import classes

/**
 * 4.1.3. Advance the current date.
 */
class DoAdvanceDate extends Command<LibraryManager> {

    DoAdvanceDate(LibraryManager receiver) {
        super(Label.ADVANCE_DATE, receiver);
        //FIXME maybe define fields
    }

    @Override
    protected final void execute() {
        int days = Form.requestInteger(Prompt.daysToAdvance());
        if (days > 0) {
            _receiver.advanceDate(days);
        }
        //FIXME implement command
    }

}
