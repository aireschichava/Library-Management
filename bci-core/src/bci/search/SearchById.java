package bci.search;

import bci.works.Work;

public final class SearchById implements SearchStrategy {

private final Work work;

    public SearchById(Work work) {
        this.work = work;
    }

    @Override
    public boolean search(String term) {
         if (term == null) return false;

         int id = Integer.parseInt(term);
    
         return work.getId() == id;
        
    }
}