package bci.app.work;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchWorkException;
import bci.works.Work;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoDisplayWork extends Command<LibraryManager> {

    DoDisplayWork(LibraryManager receiver) {
        super(Label.SHOW_WORK, receiver);

         addIntegerField("workId", Prompt.workId());
      
    }

    @Override
    protected final void execute() throws CommandException 
    {
        int id = integerField("workId");
        Work work=_receiver.getWork(id);
        if (work == null) 
		{
			throw new NoSuchWorkException(id);
		}
        
        _display.popup(work.toString());
            

    }

}
