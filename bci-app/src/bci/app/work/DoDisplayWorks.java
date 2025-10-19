package bci.app.work;

import bci.LibraryManager;
import pt.tecnico.uilib.menus.Command;
//import bci.works.Work;
//import java.util.List; //not needed since sending directly to display

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
      
          _display.popup(_receiver.showAllWorks());
          
          //send all works got from receiver(library) to display
       
    }
}
