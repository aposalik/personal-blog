package com.blog.personal_blog.service;
import com.blog.personal_blog.model.Article;
import com.blog.personal_blog.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;



@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    // Constructor injection — Spring provides the Repository
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    // GET ALL — sorted newest first
    public List<Article> getAllArticles() throws IOException {
        List<Article> articles = articleRepository.findAll();
        return articles.stream().sorted(Comparator.comparing(Article::getDate).reversed()).
                toList();
    }

    // GET ONE
    public Article getArticle(String slug) {
            Article article = articleRepository.findBySlug(slug);
            if (article == null) {
                throw new RuntimeException("Article not found: " + slug);
            }
            return article;

    }

    // CREATE — generates slug, checks for duplicates
    public void createArticle(String title, String content, LocalDate date) throws IOException {
        String slug = generateSlug(title);
        if (articleRepository.exists(slug)) {
            throw new RuntimeException("Article already exists: " + slug);
        }
        Article article = new Article(slug, title, content, date);
        articleRepository.save(article);
    }

    // UPDATE — overwrites existing article
    public void updateArticle(String slug, String title,
                              String content, LocalDate date) throws IOException {
        if(!articleRepository.exists(slug)) {
            throw new RuntimeException("Article not found: " + slug);
        }
        articleRepository.save(new Article(slug, title, content, date));
    }

    // DELETE
    public void deleteArticle(String slug) {
        if(!articleRepository.exists(slug)) {
            throw new RuntimeException("Article not found: " + slug);
        }
        articleRepository.delete(slug);
    }

    // SLUG GENERATOR — private helper
    private String generateSlug(String title) {
        return title.toLowerCase().replaceAll("\\s+", "-")
                .replaceAll("[^a-z0-9-]", "");
    }
}