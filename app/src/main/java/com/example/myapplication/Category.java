package com.example.myapplication;

public class Category {
    private String name;
    private int iconUrl; // Using int for drawable resource ID

    public Category(String name, int iconUrl) {
        this.name = name;
        this.iconUrl = iconUrl;
    }

    // Getters for your category properties
    public String getName() {
        return name;
    }

    public int getIconUrl() {
        return iconUrl;
    }

    // You can add setters if you need to modify properties after creation
}
