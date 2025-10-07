package bci.search;

import bci.works.Work;


/** * Search strategy that matches works by their price.
 */


public final class SearchByPrice implements SearchStrategy {
    private final Work work;

    public SearchByPrice(Work work) {
        this.work = work;
    }

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