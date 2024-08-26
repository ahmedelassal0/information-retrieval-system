package com.ahmedelassal.searchEngine.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileReader {
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String readDocument(String documentName) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:static/documents/" + documentName + ".txt");
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }

    public String[] readDocuments(String... documentNames) throws IOException {
        List<String> documents = new ArrayList<>();
        for (String documentName : documentNames)
            documents.add(readDocument(documentName));
        return documents.toArray(String[]::new);
    }
}