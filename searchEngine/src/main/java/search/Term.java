package search;

import lombok.Getter;

@Getter
public class Term {
    private final String term;
    private static int termsTotalNumber = 0;

    public Term(String term) {
        if (term == null || term.trim().isEmpty())
            throw new IllegalArgumentException("term cannot be null or empty");
        this.term = term;

        synchronized (Term.class) {
            termsTotalNumber++;
        }
    }

    public static int getTermsTotalNumber() {
        return termsTotalNumber;
    }
}
