package bci.search;

import bci.works.Work;

/**
 * Search strategy that matches works by their unique ID.
 */
public final class SearchById implements SearchStrategy {
    /** The work to be searched. */
    private final Work work;

    /**
     * Constructs a SearchById strategy for a given work.
     * @param work the work to search
     */
    public SearchById(Work work) {
        this.work = work;
    }

    /**
     * Searches the work by comparing its ID to the provided term.
     * @param term the search term (should be an integer)
     * @return true if the work's ID matches the term, false otherwise
     */
    @Override
    public boolean search(String term) {
         if (term == null) return false;
         int id = Integer.parseInt(term);
         return work.getId() == id;
    }
}