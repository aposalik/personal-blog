package com.blog.personal_blog.repository;
import com.blog.personal_blog.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




@Repository
public class ArticleRepository {

    private final String articlesDir;
    private final ObjectMapper objectMapper;

    // Spring injects the value from application.properties
    public ArticleRepository(@Value("${blog.articles.directory}") String articlesDir) {
        this.articlesDir = articlesDir;

        // Jackson needs this module to handle LocalDate correctly
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    // Helper — builds the File object for a given slug
    private File getArticleFile(String slug) {
        return new File(articlesDir + "/" + slug + ".json");
    }

    // READ ALL articles
    public List<Article> findAll() throws IOException {
        List<Article> articles = new ArrayList<>();
        File dir = new File(articlesDir);
        File[] files = dir.listFiles(
                (d, name) -> name.endsWith(".json") // only .json files
        );

        if (files == null) return articles;
        for (File file : files) {
            try {
                Article article = objectMapper.readValue(file,Article.class);
                articles.add(article);
            }catch (IOException e){
                System.err.println("Failed to read article file: "
                        + file.getName());
            }
        }
        return articles;
    }

    // READ ONE article by slug
    public Article findBySlug(String slug) {
        File file = getArticleFile(slug);

        if(!file.exists()) return null;
            try {
                return objectMapper.readValue(file,Article.class);
            }catch (IOException e){
                throw new RuntimeException("Failed to read article: " + slug, e);
            }
    }

    // SAVE (create or update) an article
    public void save(Article article) throws IOException {
        File file = getArticleFile(article.getSlug());
        try{
            objectMapper.writeValue(file , article);
        }catch (IOException e){
            throw new RuntimeException("Failed to write article: " + article.getSlug(), e);
        }

    }

    // DELETE an article by slug
    public void delete(String slug) {
        File file  = getArticleFile(slug);
        if(file.exists()){
            file.delete();
        }
    }

    // CHECK if article exists
    public boolean exists(String slug) {
        return getArticleFile(slug).exists();
    }
}