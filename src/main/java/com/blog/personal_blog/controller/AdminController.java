package com.blog.personal_blog.controller;
import com.blog.personal_blog.model.Article;
import com.blog.personal_blog.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ArticleService articleService;
    public AdminController(ArticleService articleService) {
        this.articleService = articleService;
    }

    // DASHBOARD — list all articles
    @GetMapping("/dashboard")
    public String dashboard(Model model) throws IOException {
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "admin/dashboard";
    }

    // SHOW add article form
    @GetMapping("/add")
    public String showAddForm() {
        return "admin/add";
    }

    // PROCESS add article form
    @PostMapping("/add")
    public String addArticle(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String date) throws IOException {
        LocalDate localDate = LocalDate.parse(date);
        articleService.createArticle(title,content,localDate);
        return "redirect:/admin/dashboard";
    }

    // SHOW edit article form
    @GetMapping("/edit/{slug}")
    public String showEditForm(@PathVariable String slug, Model model) {
        Article article = articleService.getArticle(slug);
        model.addAttribute("article", article);
        return "admin/edit";
    }

    // PROCESS edit article form
    @PostMapping("/edit/{slug}")
    public String editArticle(
            @PathVariable String slug,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String date) throws IOException {
        LocalDate localDate = LocalDate.parse(date);
        articleService.updateArticle(slug,title,content,localDate);
        return "redirect:/admin/dashboard";
    }

    // DELETE article
    @PostMapping("/delete/{slug}")
    public String deleteArticle(@PathVariable String slug) {
        articleService.deleteArticle(slug);
        return "redirect:/admin/dashboard";
    }

    // Add this to AdminController.java
    @GetMapping("/login")
    public String showLoginPage() {
        return "admin/login";
    }
}