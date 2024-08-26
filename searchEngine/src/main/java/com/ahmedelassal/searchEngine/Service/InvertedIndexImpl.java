package com.ahmedelassal.searchEngine.Service;

import org.springframework.stereotype.Service;
import com.ahmedelassal.searchEngine.search.Posting;
import com.ahmedelassal.searchEngine.search.PostingList;
import com.ahmedelassal.searchEngine.search.PostingListImpl;

import java.util.*;

@Service
public class InvertedIndexImpl implements InvertedIndex {
    HashMap<String, PostingList> data = new HashMap<>();

    @Override
    public PostingList getPostingListOf(String term) {
        if (!data.containsKey(term.toLowerCase())) {
            return null;
        }
        return data.get(term);
    }

    @Override
    public void addPostingToTerm(String term, Posting posting) {
        if (term == null || posting == null) return;

        data.computeIfAbsent(term, _ -> new PostingListImpl()).addPosting(posting);
    }

    @Override
    public void addTermsToPosting(String[] terms, Posting posting) {
        if (terms == null || posting == null) return;
        for (String term : terms) {
            addPostingToTerm(term, posting);
        }
    }

    @Override
    public boolean removePostingFromTerm(String term, Posting posting) {
        if (term == null || posting == null) return false;

        if (!data.containsKey(term)) return false;

        data.get(term).removePosting(posting);
        return true;
    }

    @Override
    public List<Posting> getUnion(String... terms) {
        Set<Posting> union = new HashSet<>();

        for (String term : terms) {
            if (!data.containsKey(term)) {
                continue;
            }
            union.addAll(data.get(term).data());
        }

        return new ArrayList<>(union);
    }

    @Override
    public List<Posting> getIntersection(String... terms) {
        if (terms == null || terms.length == 0)
            return null;

        // Check if the first term exists in the data map
        if (!data.containsKey(terms[0]) || data.get(terms[0]) == null)
            return new ArrayList<>();

        Set<Posting> intersection = new HashSet<>(data.get(terms[0]).data());

        for (String term : terms) {
            if (!data.containsKey(term) || data.get(term) == null)
                continue;

            intersection.retainAll(data.get(term).data());

            // Early exit if the intersection is empty
            if (intersection.isEmpty())
                return new ArrayList<>();
        }
        return new ArrayList<>(intersection);
    }


    @Override
    public String toString() {
        return "InvertedIndexImpl{" +
                "data=" + data +
                '}';
    }
}