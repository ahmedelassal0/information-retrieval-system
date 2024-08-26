package com.ahmedelassal.searchEngine.Service;

import com.ahmedelassal.searchEngine.search.Posting;
import com.ahmedelassal.searchEngine.search.PostingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchService {

    private final InvertedIndex invertedIndex;

    @Autowired
    public SearchService(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    public List<Posting> searchDocuments(String query, String[] excluded) {
        List<Posting> documents;
        if (query.contains("(AND)")) {
            String[] terms = Arrays
                    .stream(query.split("\\(AND\\)"))
                    .map(String::trim)
                    .toArray(String[]::new);
            documents = invertedIndex.getIntersection(terms);
        } else {
            documents = invertedIndex.getUnion(query.split(" "));
            System.out.println(documents);
        }

        if (excluded != null) {
            List<Posting> excludedPostings = invertedIndex.getUnion(excluded);
            System.out.println(excludedPostings);
            documents.removeAll(excludedPostings);
        }
        return documents;
    }

    public List<Posting> searchDocuments(String query) {
        return searchDocuments(query, null);
    }
}
