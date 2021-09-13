package com.example.dailynews;

public class NewsModel {
   static String ImageUrl;
   static String Author,NewsTitle;

    public NewsModel(String imageUrl, String author, String newsTitle) {
        NewsModel.ImageUrl = imageUrl;
        NewsModel.Author = author;
        NewsModel.NewsTitle = newsTitle;
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
