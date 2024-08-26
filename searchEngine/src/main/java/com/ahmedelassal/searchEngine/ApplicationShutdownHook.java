package com.ahmedelassal.searchEngine;

import com.ahmedelassal.searchEngine.config.AppConfig;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class ApplicationShutdownHook {
    @PreDestroy
    public void onShutdown() {
        final Path DOCUMENTS_DIR = Paths.get(AppConfig.getStaticUploadDir());
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(DOCUMENTS_DIR)) {
            for (Path path : directoryStream) {
                Files.deleteIfExists(path);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
