package bci.works;

import java.util.ArrayList;
import java.util.List;



public class Book extends Work {
    private String isbn;
  
    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    public Book(int id, String title, Category category, int totalCopies, String isbn, int price) {
        super(id, title, category, totalCopies, price);
        this.isbn = isbn;
     
    }

    public String getIsbn() {
        return isbn;
    }


    @Override
	public void addCreator(Creator creator) {
        if (!getCreators().contains(creator)) {
            getCreators().add(creator);
        }
         creator.addWork(this);
		
	}

/**this method allows to search for a term in the book's title and author's name */
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
    /** to implement the rule "A book can have multiple authors" */
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

