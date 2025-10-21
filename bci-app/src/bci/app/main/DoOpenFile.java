package bci.app.main;

import bci.LibraryManager;
import bci.app.exceptions.FileOpenFailedException;
import bci.exceptions.MissingFileAssociationException;
import bci.exceptions.UnavailableFileException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import java.io.IOException;

//FIXME maybe import classes

/**
 * §4.1.1 Open and load files.
 */
class DoOpenFile extends Command<LibraryManager> {

    DoOpenFile(LibraryManager receiver) {
        super(Label.OPEN_FILE, receiver);
        // Do not add the filename as a form field here because we may need to
        // ask the user to save first. We'll request the filename in execute()

    }

    @Override
    protected final void execute() throws CommandException {
        // Se houver alterações não salvas, perguntar ao usuário
        if (_receiver.isDirty()) {
            if (Form.confirm(Prompt.saveBeforeExit())) {
                try {
                    _receiver.save();
                } catch (MissingFileAssociationException eSave) {
                    // No associated filename: ask for a filename and save as
                    try {
                        _receiver.saveAs(Form.requestString(Prompt.newSaveAs()));
                    } catch (MissingFileAssociationException | IOException eSaveAs) {
                        eSaveAs.printStackTrace();
                    }
                } catch (IOException eSave) {
                    eSave.printStackTrace();
                }
            }
        }

        // Now request the filename (after the save-confirmation prompt)
        String filename = Form.requestString(Prompt.openFile());

        try {
            _receiver.load(filename);
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }

}
