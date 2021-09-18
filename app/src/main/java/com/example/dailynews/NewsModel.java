package com.example.dailynews;

public class NewsModel {
    String ImageUrl;
    String Author,NewsTitle,sourceName;
    String newsLink;


    public NewsModel(String imageUrl, String author, String newsTitle, String source,String link) {
        this.ImageUrl = imageUrl;
        this.Author = author;
        this.NewsTitle = newsTitle;
        this.sourceName=source;
        this.newsLink=link;
    }

    public String getNewsLink() {
        return this.newsLink;
    }
    public String getImageUrl() {
        return this.ImageUrl;
    }

    public String getAuthor() {
        return this.Author;
    }

    public String getNewsTitle() {
        return this.NewsTitle;
    }

    public String getSource() {
        return this.sourceName;
    }
}
