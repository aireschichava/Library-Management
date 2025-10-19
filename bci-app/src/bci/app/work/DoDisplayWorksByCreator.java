package bci.app.work;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchCreatorException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//import java.util.List;
//import bci.works.Work;
//these imports are not needed since sending directly to display



/**
 * 4.3.3. Display all works by a specific creator.
 */
class DoDisplayWorksByCreator extends Command<LibraryManager> 
{

    DoDisplayWorksByCreator(LibraryManager receiver) 
    {
        super(Label.SHOW_WORKS_BY_CREATOR, receiver);

        addStringField("creatorId", Prompt.creatorId());
    }

    @Override
    protected final void execute() throws CommandException 
    {
        String creatorId = stringField("creatorId");
        
        
        if(_receiver.getCreator(creatorId) == null)
        {
			throw new NoSuchCreatorException(creatorId);
		}
        
        // Call the manager(_receiver) to get works by creator, and then send to displa
                _display.popup( _receiver.searchWorksByCreator(creatorId));
            
    }

    

}
