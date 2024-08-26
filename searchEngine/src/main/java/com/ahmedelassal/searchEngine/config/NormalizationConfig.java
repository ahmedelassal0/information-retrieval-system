package com.ahmedelassal.searchEngine.config;

import java.util.Set;

public class NormalizationConfig {
    public static final Set<Character> REMOVED_CHARS = Set.of(
            '\'', '\\', '/', '"', '{', '}', '[', ']', '.'
    );
    public static final Set<String> STOP_WORDS = Set.of(
            "a", "an", "and", "the", "of", "in", "to", "for", "with", "on",
            "at", "by", "from", "as", "is", "was", "were", "be", "been", "has",
            "have", "had", "having", "that", "which", "who", "whom", "whose",
            "it", "its", "this", "these", "those", "or", "but", "not", "are",
            "am", "will", "shall", "may", "might", "could", "should", "would",
            "can", "must", "do", "does", "did", "doing", "such", "only", "more",
            "some", "any", "no", "every", "each", "few", "less", "most", "many"
    );
}
