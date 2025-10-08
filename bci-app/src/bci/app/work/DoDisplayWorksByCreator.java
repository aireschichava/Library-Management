package bci.app.work;

import bci.LibraryManager;
import bci.app.exceptions.NoSuchCreatorException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.util.List;
import bci.works.Work;

//FIXME maybe import classes

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
        
        // Call the manager to get works by creator
        List<Work> works = _receiver.searchWorksByCreator(creatorId);
        if (works != null && !works.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < works.size(); i++) {
                Work w = works.get(i);
                if (w == null) continue;
                sb.append(w.toString());
                if (i < works.size() - 1) sb.append("\n");
            }
            if (sb.length() > 0) {
                _display.popup(sb.toString());
            }
        }
        //to implement exception UnknownCreatorException
    }

}
