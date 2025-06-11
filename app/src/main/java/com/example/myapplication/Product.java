// app/src/main/java/com/example/myapplication/models/Product.java
// Make sure this package name matches the one you just created
package com.example.myapplication;

public class Product {
    private String productName;
    private String productPrice;
    private int inStock;
    private int partyDiscount;
    private int individualDiscount;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getFlavourName() {
        return flavourName;
    }

    public void setFlavourName(String flavourName) {
        this.flavourName = flavourName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public int getIndividualDiscount() {
        return individualDiscount;
    }

    public void setIndividualDiscount(int individualDiscount) {
        this.individualDiscount = individualDiscount;
    }

    public int getPartyDiscount() {
        return partyDiscount;
    }

    public void setPartyDiscount(int partyDiscount) {
        this.partyDiscount = partyDiscount;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private String productImageUrl;
    private String brandName;
    private String flavourName;
    private String categoryName;

}