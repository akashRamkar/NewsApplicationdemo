package com.example.dailynews;

public class NewsModel {
    String ImageUrl;
    String Author,NewsTitle;

    public NewsModel(String imageUrl, String author, String newsTitle) {
        this.ImageUrl = imageUrl;
        this.Author = author;
        this.NewsTitle = newsTitle;
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


}
