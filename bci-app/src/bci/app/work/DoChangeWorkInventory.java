package bci.app.work;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchWorkException;
import bci.exceptions.InvalidInventoryUpdateException;
import bci.works.Work;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * 4.3.4. Change the number of exemplars of a work.
 */
class DoChangeWorkInventory extends Command<LibraryManager> {

    DoChangeWorkInventory(LibraryManager receiver) {
        super(Label.CHANGE_WORK_INVENTORY, receiver);
        addIntegerField("id", Prompt.workId());
        addIntegerField("amount", Prompt.amountToUpdate());
    }

    @Override
    protected final void execute() throws CommandException {
        int id = integerField("id");
        int amount = integerField("amount");

        Work work = _receiver.getWork(id);
        if (work == null) {
            throw new NoSuchWorkException(id);
        }
        try {
            _receiver.updateInventory(work, amount);
        } catch (InvalidInventoryUpdateException e) {
            _display.popup(Message.notEnoughInventory(id, amount));
        }
    }

}
