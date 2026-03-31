package com.blog.personal_blog.controller;
import com.blog.personal_blog.model.Article;
import com.blog.personal_blog.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.util.List;

@Controller
public class GuestController {

    private final ArticleService articleService;

    // Constructor injection
    public GuestController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // Home page — show all articles
    @GetMapping("/")
    public String homePage(Model model) throws IOException {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "index";

    }
    // Single article page
    @GetMapping("/article/{slug}")
    public String articlePage(@PathVariable String slug, Model model) {
        Article article = articleService.getArticle(slug);
        model.addAttribute("article", article);
        return "article";
    }
}
