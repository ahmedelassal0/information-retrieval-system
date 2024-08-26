package com.ahmedelassal.searchEngine.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface PostingList {

    List<Posting> data();

    void addPosting(Posting posting);

    void removePosting(Posting posting);
}
