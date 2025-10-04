package bci;

import bci.exceptions.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import bci.user.*;
//FIXME maybe import classes

/** Class that represents the library as a whole. */
class Library implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    private int _currentDate = 1;

    private List <User> _users = new ArrayList<>();

    //FIXME maybe define attributes
    //FIXME maybe implement constructor
    //FIXME maybe implement methods

    public int getCurrentDate() {
        return _currentDate;
    }

    public void advanceDate(int days) {
        _currentDate += days;
        updateUserStatus();
    }

    private void updateUserStatus() {
        //FIXME implement method
    }

    public void addUser(User user) {
        _users.add(user);
    }

    public User getUser(int id) {
        for (User user : _users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    public String showUser(int id) {
        User user = getUser(id);  // Usar método existente
        return user.toDisplayString();
    }
    
    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename name of the file to load
     * @throws UnrecognizedEntryException
     * @throws IOException
     * FIXME maybe other exceptions
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException
	    /* FIXME maybe other exceptions */  {
      System.out.print("teste");
      //to be deleted
    }

}
