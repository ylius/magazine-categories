package com.example.subscription;

public class Category {
    private String image;
    private String title;
    private boolean isSubscribed;

    public Category(String image, String title, boolean isSubscribed) {
        this.image = image;
        this.title = title;
        this.isSubscribed = isSubscribed;
    }

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }
}
