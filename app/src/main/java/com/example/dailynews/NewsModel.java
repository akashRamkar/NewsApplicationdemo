package com.example.dailynews;

public class NewsModel {
   String ImageUrl;
   String Author,NewsTitle;

    public NewsModel(String imageUrl, String author, String newsTitle) {
        ImageUrl = imageUrl;
        Author = author;
        NewsTitle = newsTitle;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }
}
