package com.example.retrofittry;

import java.io.Serializable;

public class News implements Serializable {
    String title, content, imageUrl;

    public News(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
