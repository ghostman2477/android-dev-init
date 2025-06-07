// app/src/main/java/com/example/myapplication/models/Product.java
// Make sure this package name matches the one you just created
package com.example.myapplication;

public class Product {
    private String name;
    private String price;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int imageUrl;
    private int quantity;  // Using int for drawable resource ID

    public Product(String name, String price, int imageUrl,int quantity) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    // Getters for your product properties
    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public int getQuantity() {return quantity;}

    // You can add setters if you need to modify properties after creation
    // public void setName(String name) { this.name = name; }
    // public void setPrice(String price) { this.price = price; }
    // public void setImageUrl(int imageUrl) { this.imageUrl = imageUrl; }
}