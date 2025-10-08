package bci.works;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a creator (author, director, etc.) in the library system.
 */
public class Creator implements Serializable {
    private static final long serialVersionUID = 202507171003L;

    /** Name of the creator. */
    private String name;
    /** List of works associated with this creator. */
    private List<Work> works;

    /**
     * Constructs a new Creator with the given name.
     * @param name the name of the creator
     */
    public Creator(String name) {
        this.name = name;
        this.works = new ArrayList<>();
    }

    /**
     * Returns the name of the creator.
     * @return the creator's name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the list of works associated with this creator.
     * @return list of works
     */
    public List<Work> getWorks() {
        return works;
    }

    /**
     * Adds a work to the creator's list if not already present.
     * @param work the work to add
     */
    public void addWork(Work work) {
        if (!works.contains(work)) {
            works.add(work);
        }
    }

    /**
     * Removes a work from the creator's list.
     * @param work the work to remove
     */
    public void removeWork(Work work) {
        works.remove(work);
    }

    /**
     * Checks if the creator has any works associated.
     * @return true if the creator has works, false otherwise
     */
    public boolean hasWorks() {
        return !works.isEmpty();
    }
}