package bci.works;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a Book in the library.
 */
public class Book extends Work {
    /** ISBN number of the book. */
    private String isbn;
  
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    /**
     * Constructs a new Book.
     * @param id unique identifier
     * @param title title of the book
     * @param category category of the book
     * @param totalCopies total number of copies
     * @param isbn ISBN number
     * @param price price of the book
     */
    public Book(int id, String title, Category category, int totalCopies, String isbn, int price) {
        super(id, title, category, totalCopies, price);
        this.isbn = isbn;
     
    }

    /**
     * Returns the ISBN number of the book.
     * @return the ISBN number
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Adds an author to the book.
     * @param creator the author to add
     */
    @Override
	public void addCreator(Creator creator) {
        if (!getCreators().contains(creator)) {
            getCreators().add(creator);
        }
         creator.addWork(this);
		
	}

    /**
     * Searches for a term in the book's title or authors' names.
     * @param term the search term
     * @return true if the term matches, false otherwise
     */
    @Override
    public boolean search(String term) {
        String lowerTerm = term.toLowerCase();
        if (getTitle().toLowerCase().contains(lowerTerm)) {
            return true;
        }
        for (Creator author : getCreators()) {
            if (author.getName().toLowerCase().contains(lowerTerm)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns a string representation of the book, including authors.
     * @return string representation
     */
    @Override
    public String toString() {
        List<String> authorNames = new ArrayList<>();
        for (Creator author : getCreators()) {
            authorNames.add(author.getName());
        }
        String authors = String.join("; ", authorNames);
        
        return getId() + " - " + getAvailableCopies() + " de " + getTotalCopies() + " - Livro - " + getTitle() + " - " +
               getPrice() + " - " + getCategory() + " - " + authors + " - " + getIsbn();
    }

}