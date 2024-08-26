package com.ahmedelassal.searchEngine.search;

import java.util.*;

public class PostingListImpl implements PostingList {
    private final List<Posting> data = new ArrayList<>();

    @Override
    public List<Posting> data() {
        return Collections.unmodifiableList(data);
    }

    @Override
    public void addPosting(Posting posting) {
        if (posting == null) {
            throw new IllegalArgumentException("posting cannot be null");
        }
        if (data.contains(posting)) {
            return;
        }
        data.add(posting);
        data.sort(Comparator.comparingLong(Posting::getId));
        System.out.println(data);
    }

    @Override
    public void removePosting(Posting posting) {
        if (posting == null) {
            throw new IllegalArgumentException("posting cannot be null");
        }
        data.remove(posting);
    }

    @Override
    public String toString() {
        return "PostingListImpl{" +
                "data=" + data +
                '}';
    }
}
