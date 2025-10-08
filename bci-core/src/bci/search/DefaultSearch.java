package bci.search;

import bci.works.Work;

/**
 * Default search strategy that searches by title and creator's name.
 * Delegates the search to the concrete Work implementation.
 */
public final class DefaultSearch implements SearchStrategy {
    /** The work to be searched. */
    private final Work work;

    /**
     * Constructs a DefaultSearch strategy for a given work.
     * @param work the work to search
     */
    public DefaultSearch(Work work) {
        this.work = work;
    }

    /**
     * Searches the work using the provided term.
     * @param term the search term
     * @return true if the work matches the term, false otherwise
     */
    @Override
    public boolean search(String term) {
        return work.search(term); // delegate to the concrete Work (Book/DVD)
    }
}