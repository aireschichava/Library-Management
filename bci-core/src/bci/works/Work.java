package bci.works;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that represents a work (book, DVD, etc.) in the library.
 */
public abstract class Work implements Serializable {
    /** Serial version UID for serialization. */
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
    
    /** Unique identifier for the work. */
    private int id;
    /** Title of the work. */
    private String title;
    /** List of creators (authors, directors, etc.) associated with the work. */
    private List<Creator> creators;
    /** Total number of copies of the work. */
    private int totalCopies;
    /** Number of available copies for loan. */
    private int availableCopies;
    /** Category of the work. */
    private Category category;
    /** Price of the work. */
    private int price;

    /**
     * Constructs a new Work.
     * @param id unique identifier
     * @param title title of the work
     * @param category category of the work
     * @param totalCopies total number of copies
     * @param price price of the work
     */
    public Work(int id, String title, Category category, int totalCopies, int price) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
        this.price = price;
        this.creators = new ArrayList<>();
    }

    /** @return the unique identifier of the work */
    public int getId() {
        return id;
    }

    /** @return the title of the work */
    public String getTitle() {
        return title;
    }

    /** @return the list of creators associated with the work */
    public List<Creator> getCreators() {
        return creators;
    }

    /** @return the total number of copies of the work */
    public int getTotalCopies() {
        return totalCopies;
    }

    /** @return the number of available copies for loan */
    public int getAvailableCopies() {
        return availableCopies;
    }

    /** @return the category of the work */
    public Category getCategory() {
        return category;
    }
    /** @return the price of the work */
    public int getPrice() {
        return price;
    }

    /**
     * Checks if the work is available for loan.
     * @return true if available, false otherwise
     */
    public boolean isAvailable() {
        return availableCopies > 0;
    }

    /**
     * Adds a creator to the work.
     * @param creator the creator to add
     */
    public abstract void addCreator(Creator creator);

    /**
     * Updates the inventory for this work item.
     * @param amount the amount to update the inventory by (can be negative)
     * @return true if the update was successful, false otherwise
     */
    public boolean updateInventory(int amount) {
        if (availableCopies + amount >= 0 && totalCopies + amount >= 0) {
            availableCopies += amount;
            totalCopies += amount;
            return true;
        }
        return false;
    }

    /**
     * Searches for a term in the work's data.
     * @param term the search term
     * @return true if the term matches, false otherwise
     */
    public abstract boolean search(String term);

}