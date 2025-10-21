package bci;

import bci.exceptions.*;
import bci.user.User;
import bci.works.Creator;
import bci.works.Work;
import java.io.*;
import java.util.List;

/**
 * The façade class.
 */
public class LibraryManager {

    /** The object doing all the actual work. */
   // private Library _library = new Library(_defaultRules);

    private Library library = new Library();

    /** The filename associated with the current library state (for save/load). */
    private String _filename = null;

    /**
     * Saves the current library state to the associated file.
     * @throws MissingFileAssociationException if no file is associated with the current state
     * @throws IOException if some problem occurs during the process
     */
    public void save() throws MissingFileAssociationException, IOException {
        if (_filename == null || _filename.isEmpty()) {
            throw new MissingFileAssociationException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(_filename)))) {
            oos.writeObject(library);
        }
    }

    public void saveAs(String filename) throws MissingFileAssociationException, IOException {
        _filename = filename;
        save();
    }



    /**
     * Loads library state from a file, replacing the current library.
     * @param filename name of the file
     * @throws UnavailableFileException if the file is not available or some other problem occurs
     *         during the process.
     */
    
    public void load(String filename) throws UnavailableFileException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(filename)))) {

            library = (Library) ois.readObject();
            _filename = filename;
        } catch (IOException | ClassNotFoundException e) {
            throw new UnavailableFileException(filename);
        }
    }

    /**
     * Read text input file and initializes the current library (which should be empty)
     * with the domain entities representeed in the import file.
     *
     * @param filename name of the text input file
     * @throws ImportFileException if some error happens during the processing of the
     * import file.
     */
    public void importFile(String filename) throws ImportFileException {
        try {
            if (filename != null && !filename.isEmpty()) {
                library.importFile(filename);
            }
        } catch (IOException | UnrecognizedEntryException e) {
            throw new ImportFileException(filename, e);
        }
    }



    /**
     * Search works by a term, delegating to Library
     * 
     */
  
    public void addWork(Work work) {
        library.addWork(work);
    }

    /**
     * Returns all works in the library, sorted by their ID.
     * @return list of all works
     */
    public List<Work> showAllWorks() {
        return library.getAllWorks();

    }
    /**
     * Search works by a term, delegating to Library
     * @param term
     * @return
     */
    public List<Work> searchWorks(String term) {
        return library.searchWorks(term);
    }

    /**
     * Search works by price, delegating to Library
     * @param price
     * @return
     */
    public List<Work> searchWorksByPrice(int price) {
        return library.searchWorksByPrice(price); 
    }
    
    /**
     * Get a work by its ID, or null if not found.
     * @param id
     * @return
     */
    public Work getWork(int id) {
        return library.getWork(id);
    }

    /**
     * Get a creator by name, or null if not found.
     * @param name
     * @return
     */
    public Creator getCreator(String name) {
        return library.getCreator(name);
    }

    public List<Work> searchWorksByCreator(String creatorName) {
        return library.searchWorksByCreator(creatorName);
}

    /**
     * Change inventory for a work and propagate domain exception on invalid change.
     */
    public void updateInventory(Work work, int amount) throws InvalidInventoryUpdateException {
        library.updateInventory(work, amount);
    }


    /** Returns the current logical date. */
    public int getCurrentDate() {
        return library.getCurrentDate();
    }

    /** Advances the current logical date by a positive number of days. */
    public void advanceDate(int days) {
        library.advanceDate(days);
    }

    /**
     * Registers a new user, given the name and email.
     * @param name the user's name
     * @param email the user's email
     * @return the created user, or null if registration failed
     */
    public User registerUser(String name, String email) {
        int id = library.getNextUserId();
        User user = new User(id, name, email);
        library.addUser(user);
        return user;
    }

    public String showUser(int id) {
        return library.showUser(id);
    }

    public User getUser(int id) {
        return library.getUser(id);
    }

    public List<User> getAllUsers() {
        return library.getAllUsers();
    }

}
