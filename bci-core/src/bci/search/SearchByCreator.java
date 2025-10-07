package bci.search;
import bci.works.Creator;
import bci.works.Work;

/**
 * Search strategy that matches works by their creator name.
 */
public final class SearchByCreator implements SearchStrategy {
    private final Work work;

    public SearchByCreator(Work work) {
        this.work = work;
    }

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