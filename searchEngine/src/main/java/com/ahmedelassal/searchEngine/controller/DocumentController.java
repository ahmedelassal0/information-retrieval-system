package com.ahmedelassal.searchEngine.controller;

import com.ahmedelassal.searchEngine.Service.InvertedIndex;
import com.ahmedelassal.searchEngine.Service.SearchService;
import com.ahmedelassal.searchEngine.search.Posting;
import com.ahmedelassal.searchEngine.util.Documents;
import com.ahmedelassal.searchEngine.util.FileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("documents")
public class DocumentController {
    private final InvertedIndex invertedIndex;
    private final FileReader fileReader;
    private final SearchService searchService;

    @Autowired
    public DocumentController(InvertedIndex invertedIndex, FileReader fileReader, SearchService searchService) {
        this.invertedIndex = invertedIndex;
        this.fileReader = fileReader;
        this.searchService = searchService;
    }

    @GetMapping("/{document}")
    public String show(@PathVariable(name = "document") String documentUri, Model model) throws IOException {
        model.addAttribute("document", fileReader.readDocument(documentUri));
        return "documents/show";
    }

    @GetMapping("")
    public String index(
            @RequestParam(name = "query") String query,
            @RequestParam(name = "excluded", required = false) String excluded,
            Model model
    ) {
        String[] excludedArr =
                excluded == null ? null
                        :
                        Arrays
                                .stream(excluded.split(","))
                                .map(String::trim)
                                .toArray(String[]::new);

        List<Posting> documents = searchService.searchDocuments(query, excludedArr);
        model.addAttribute("documents", documents);

        return "documents/index";
    }

    @GetMapping("/add")
    public String create() {
        return "documents/add";
    }

    @PostMapping("/store")
    public String store(@RequestParam(name = "documents") MultipartFile[] documents) throws IOException {
        for (MultipartFile document : documents) {
            if (document.isEmpty()) continue;

            String[] normalizedTerms = Documents.normalize(Documents.tokenize(document));

            String fileName = Documents.save(document).getFileName().toString().split("\\.")[0];

            invertedIndex.addTermsToPosting(normalizedTerms, new Posting(fileName, document.getOriginalFilename()));
        }
        return "redirect:/documents/add";
    }
}
