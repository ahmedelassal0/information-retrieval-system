package com.ahmedelassal.searchEngine.Service;

import com.ahmedelassal.searchEngine.search.Posting;
import com.ahmedelassal.searchEngine.search.PostingList;

import java.util.List;
import java.util.Set;

public interface InvertedIndex {
    PostingList getPostingListOf(String term);
    void addPostingToTerm(String term, Posting posting);
    void addTermsToPosting(String[] term, Posting posting);

    boolean removePostingFromTerm(String term, Posting posting);

    List<Posting> getUnion(String... terms);
    List<Posting> getIntersection(String... terms);
}
