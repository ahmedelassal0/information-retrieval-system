package com.ahmedelassal.searchEngine.search;

import com.ahmedelassal.searchEngine.util.SlugifyUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class Posting {
    private final long id;
    private final String documentUri;
    private final String title;
    @Getter
    private static long documentTotal = 0;

    public Posting(String documentUri, String title) {
        if (documentUri == null || documentUri.trim().isEmpty()) {
            throw new IllegalArgumentException("Document URI cannot be null or empty");
        }

        this.documentUri = documentUri;
        this.title = SlugifyUtil.slugify(title.replace(".txt", ""));
        this.id = ++documentTotal;
    }

    @Override
    public String toString() {
        return "Posting{" +
                "id=" + id +
                ", documentUri='" + documentUri + '\'' +
                '}';
    }
}
