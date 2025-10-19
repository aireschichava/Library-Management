package bci.works;

/**
 * Class representing a DVD in the library.
 */
public class DVD extends Work {
    /** IGAC number of the DVD. */
    private String igacNumber;
    /** Director of the DVD. */
    private Creator director;

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
 
    /**
     * Constructs a new DVD.
     * @param id unique identifier
     * @param title title of the DVD
     * @param category category of the DVD
     * @param totalCopies total number of copies
     * @param igacNumber IGAC number
     * @param price price of the DVD
     */
    public DVD(int id, String title, Category category, int totalCopies, String igacNumber, int price) {
        super(id, title, category, totalCopies, price);
        this.igacNumber = igacNumber;
    }

    /**
     * Returns the IGAC number of the DVD.
     * @return the IGAC number
     */
    public String getIgacNumber() {
        return igacNumber;
    }

    /**
     * Returns the director of the DVD.
     * @return the director
     */
    public Creator getDirector() {
        return getCreators().isEmpty() ? null : getCreators().get(0);
    }

    /**
     * Searches for a term in the DVD's title or director's name.
     * @param term the search term
     * @return true if the term matches, false otherwise
     */
    @Override
    public boolean search(String term) {
        String lowerTerm = term.toLowerCase();
        if (getTitle().toLowerCase().contains(lowerTerm)) {
            return true;
        }
        return director != null && director.getName().toLowerCase().contains(lowerTerm);
    }

	/**
     * Adds a director to the DVD.
     * @param creator the director to add
     */
	@Override
	public void addCreator(Creator creator) {
        if (creator == null) return;

        getCreators().clear(); // DVDs have only one director(so clear any existing)
        getCreators().add(creator);
        creator.addWork(this);
  
		
	}

    /**
     * Returns a string representation of the DVD, including director.
     * @return string representation
     */
    @Override
    public String toString() {
        return getId() + " - " + getAvailableCopies() + " de " + getTotalCopies() + " - DVD - " + getTitle() + " - " +
               getPrice() + " - " + getCategory() + " - " + getDirector().getName() + " - " + getIgacNumber();
    }




}