package bci.search;

import bci.works.Work;

/**
 * Search strategy that matches works by their price.
 */
public final class SearchByPrice implements SearchStrategy {
    /** The work to be searched. */
    private final Work work;

    /**
     * Constructs a SearchByPrice strategy for a given work.
     * @param work the work to search
     */
    public SearchByPrice(Work work) {
        this.work = work;
    }

    /**
     * Searches the work by comparing its price to the provided term.
     * @param term the search term (should be an integer)
     * @return true if the work's price matches the term, false otherwise
     */
    @Override
    public boolean search(String term) {
        if (term == null) return false;
        try {
            int target = Integer.parseInt(term.trim());
            return work.getPrice() == target;
        } catch (NumberFormatException e) {
            return false; // term is not a valid integer
        }
    }
}