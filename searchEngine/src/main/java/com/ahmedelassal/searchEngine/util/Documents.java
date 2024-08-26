package com.ahmedelassal.searchEngine.util;

import com.ahmedelassal.searchEngine.config.AppConfig;
import com.ahmedelassal.searchEngine.config.NormalizationConfig;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

public class Documents {
    public static boolean validate(MultipartFile document) {
        if (document == null)
            return false;

        if (!document.getContentType().equals("text/plain"))
            return false;

        if (document.isEmpty())
            return false;

        return true;
    }

    public static String[] tokenize(MultipartFile document) throws IOException {
        var terms = new String(document.getBytes(), StandardCharsets.UTF_8);
        return terms.split(" ");
    }

    public static String[] normalize(String[] tokens) {
        return Arrays.stream(tokens)
                .map(String::toLowerCase)
                .map(token -> {
                    for (char ch : NormalizationConfig.REMOVED_CHARS) {
                        token = token.replace(Character.toString(ch), "");
                    }
                    return token;
                })
                .filter(token -> !token.isEmpty())
                .filter(token -> !NormalizationConfig.STOP_WORDS.contains(token))
                .toArray(String[]::new);
    }

    public static Path save(MultipartFile document) throws IOException {
        String UPLOAD_DIR = AppConfig.getStaticUploadDir();
        // Ensure the directory exists
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate a UUID and append it to the file name
        String originalFileName = document.getOriginalFilename();
        String fileExtension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        String fileNameWithUUID = UUID.randomUUID().toString() + fileExtension;

        // Create the target file path
        Path path = Paths.get(UPLOAD_DIR + fileNameWithUUID);

        // Save the file
        Files.write(path, document.getBytes());
        return path;
    }
}
