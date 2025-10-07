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
        addIntegerField("days", Prompt.daysToAdvance());
    }

    @Override
    protected final void execute() {
<<<<<<< HEAD
        int days = integerField("days");
        _receiver.advanceDate(days);
=======
        int days = Form.requestInteger(Prompt.daysToAdvance());
        if (days > 0) {
            _receiver.advanceDate(days);
        }
        //FIXME implement command
>>>>>>> 90dee6fa8b40c8f729c236159352f290a4893f73
    }

}
