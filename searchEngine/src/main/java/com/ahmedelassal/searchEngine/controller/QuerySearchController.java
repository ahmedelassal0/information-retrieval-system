package com.ahmedelassal.searchEngine.controller;

import com.ahmedelassal.searchEngine.Service.SearchService;
import com.ahmedelassal.searchEngine.util.FileReader;
import com.ahmedelassal.searchEngine.Service.InvertedIndex;
import com.ahmedelassal.searchEngine.search.Posting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class QuerySearchController {
    //  method to render form for entering a search.html query
    @GetMapping("/")
    public String index() {
        return "search";
    }
}
