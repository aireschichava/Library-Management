package bci.app.main;

import bci.LibraryManager;
import bci.exceptions.MissingFileAssociationException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.forms.Form;
import java.io.FileNotFoundException;
import java.io.IOException;
//FIXME maybe import classes

/**
 * §4.1.1 Open and load files.
 */
class DoSaveFile extends Command<LibraryManager> {

    DoSaveFile(LibraryManager receiver) {
        super(Label.SAVE_FILE, receiver);
	// no fields; we will ask for filename only if needed
    }

    @Override
    protected final void execute() {
        try {
            _receiver.save();
        } catch (MissingFileAssociationException eSave) {
            try {
                _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
            } catch (MissingFileAssociationException eSaveAs) {
                eSaveAs.printStackTrace();
            } catch (java.io.FileNotFoundException aSaveAs) {
                aSaveAs.printStackTrace();
            } catch (java.io.IOException eSaveAs) {
                eSaveAs.printStackTrace();
            }
        } catch (FileNotFoundException aSave) {
            aSave.printStackTrace();
        } catch (IOException eSave) {
            eSave.printStackTrace();
        }
    }

}
