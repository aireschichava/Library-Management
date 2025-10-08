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
        String target = term.trim();
        if (target.isEmpty()) return false;
        for (Creator creator : work.getCreators()) {
            String name = creator.getName();
            if (name != null && name.equalsIgnoreCase(target)) {
                return true;
            }
        }
        return false;
    }
}