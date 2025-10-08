package bci.app.work;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;

//FIXME maybe import classes

/**
 * 4.3.2. Display all works.
 */
class DoDisplayWorks extends Command<LibraryManager> {

    DoDisplayWorks(LibraryManager receiver) {
        super(Label.SHOW_WORKS, receiver);
	//FIXME maybe define fields
    }

    @Override
    protected final void execute() {
        java.util.List<bci.works.Work> works = _receiver.showAllWorks();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < works.size(); i++) {
            bci.works.Work w = works.get(i);
            if (w == null) continue;
            sb.append(w.toString());
            if (i < works.size() - 1) sb.append("\n");
        }
        if (sb.length() > 0) {
            _display.popup(sb.toString());
        }
    }
}
