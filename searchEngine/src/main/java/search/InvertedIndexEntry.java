package search;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;

@Getter
@AllArgsConstructor
public class InvertedIndexEntry {
    private final Term term;
    private List<Document> postingList;

    public List<Document> addDocument(Document document) {
        postingList.add(document);
        postingList.sort(Comparator.comparingLong(Document::getId));
        return postingList;
    }

    public List<Document> removeDocument(Document document) {
        postingList.remove(document);
        return postingList;
    }
}
