package com.ahmedelassal.searchEngine.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {
    @Value("${app.documents.upload-dir}")
    private String uploadDir;

    @Getter
    private static String staticUploadDir;

    @PostConstruct
    public void init() {
        staticUploadDir = uploadDir;
    }

}
