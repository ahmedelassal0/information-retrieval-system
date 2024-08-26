package com.ahmedelassal.searchEngine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class QuerySearchController {
    //  method to render form for entering a search.html query
    @GetMapping("/")
    public String index() {
        return "search";
    }
}
