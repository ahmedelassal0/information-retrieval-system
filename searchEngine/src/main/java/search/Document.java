package search;

import lombok.Getter;

@Getter
public class Document {
    private final long id;
    private final String ref;
    private static int documentsTotalNumber = 0;

    public Document(String ref) {
        if (ref == null || ref.trim().isEmpty())
            throw new IllegalArgumentException("ref and ref of the document cannot be empty");
        this.ref = ref;

        id = ++documentsTotalNumber;
    }


}
