package com.ahmedelassal.searchEngine.util;

import java.text.Normalizer;

public class SlugifyUtil {

    public static String slugify(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Normalize the input to decompose special characters
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        // Remove accents and other diacritical marks
        String ascii = normalized.replaceAll("\\p{M}", "");
        // Convert to lowercase and replace spaces and non-alphanumeric characters with hyphens
        String slug = ascii.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                .replaceAll("[\\s-]+", "-");

        return slug;
    }
}
