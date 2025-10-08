package bci.search;
import bci.works.Creator;
import bci.works.Work;

/**
 * Search strategy that matches works by their creator name.
 */
public final class SearchByCreator implements SearchStrategy {
    /** The work to be searched. */
    private final Work work;

    /**
     * Constructs a SearchByCreator strategy for a given work.
     * @param work the work to search
     */
    public SearchByCreator(Work work) {
        this.work = work;
    }

    /**
     * Searches the work's creators for a name matching the provided term.
     * @param term the search term
     * @return true if any creator's name matches the term, false otherwise
     */
    @Override
    public boolean search(String term) {
        if (term == null || work == null) return false;
        String lowerTerm = term.toLowerCase();
        for (Creator creator : work.getCreators()) {
            if (creator.getName().toLowerCase().contains(lowerTerm)) {
                return true;
            }
        }
        return false;
    }
}