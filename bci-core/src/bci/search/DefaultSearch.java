package bci.search;

import bci.works.Work;


/**
 * Default search strategy that searches by title and creator's name.
 */

public final class DefaultSearch implements SearchStrategy {
    private final Work work;

    public DefaultSearch(Work work) {
        this.work = work;
    }

    @Override
    public boolean search(String term) {
        return work.search(term); // delegate to the concrete Work (Book/DVD)
    }
}