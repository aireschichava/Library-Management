package bci.works;

/**
 * Class representing a DVD in the library.
 */

public class DVD extends Work {
    private String igacNumber;
    private Creator director;

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;
 
    public DVD(int id, String title, Category category, int totalCopies, String igacNumber, Creator director, int price) {
        super(id, title, category, totalCopies, price);
        this.igacNumber = igacNumber;
        linkDirector(director); 
    }

    public String getIgacNumber() {
        return igacNumber;
    }

    public Creator getDirector() {
        return director;
    }

    @Override
    public boolean search(String term) {
        String lowerTerm = term.toLowerCase();
        if (getTitle().toLowerCase().contains(lowerTerm)) {
            return true;
        }
        return director != null && director.getName().toLowerCase().contains(lowerTerm);
    }


	@Override
	public void addCreator(Creator creator) {
        linkDirector(creator);
		
	}

    /**
     * Links a director to this DVD and ensures bidirectional association.
     * helper method
     * @param creator
     */
    private void linkDirector(Creator creator) {
        if (creator == null) return;
        this.director = creator;
        if (!getCreators().contains(creator)) {
            getCreators().add(creator);
        }
        creator.addWork(this);
    }

    @Override
    public String toString() {
        return getId() + " - " + getAvailableCopies() + " de " + getTotalCopies() + " - DVD - " + getTitle() + " - " +
               getPrice() + " - " + getCategory() + " - " + getDirector().getName() + " - " + getIgacNumber();
    }




}
