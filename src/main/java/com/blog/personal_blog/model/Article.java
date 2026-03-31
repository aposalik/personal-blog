package com.blog.personal_blog.model;
import java.time.LocalDate;

public class Article {
    private String slug;
    private String title;
    private String content;
    private LocalDate date;


    // TODO: add remaining fields

    // TODO: no-arg constructor
    public Article(){
    }
    // TODO: full constructor (all fields)
    public Article(String slug, String title, String content, LocalDate date) {
        this.slug = slug;
        this.title = title;
        this.content = content;
        this.date = date;
    }



    // TODO: getters and setters
    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}