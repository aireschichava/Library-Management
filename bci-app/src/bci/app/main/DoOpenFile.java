package bci.app.main;

import bci.LibraryManager;
import bci.app.exceptions.FileOpenFailedException;
import bci.exceptions.UnavailableFileException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME maybe import classes

/**
 * §4.1.1 Open and load files.
 */
class DoOpenFile extends Command<LibraryManager> {

    DoOpenFile(LibraryManager receiver) {
        super(Label.OPEN_FILE, receiver);
	addStringField("filename", Prompt.openFile());
    }

    @Override
    protected final void execute() throws CommandException {
        String filename = stringField("filename");
        try {
            _receiver.load(filename);
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }

}
