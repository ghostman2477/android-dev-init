package com.example.myapplication;

public class CartItem {
    private String id;
    private String imageUrl;
    private String name;
    private String packInfo; // e.g., "1 pack (3 pieces)"
    private double price; // Price for the current quantity (per unit)
    private int quantity; // Quantity of this item in the cart
    private boolean saveForLater; // For the "Save for later" functionality (if implemented)

    public CartItem(String id, String imageUrl, String name, String packInfo, double price, int quantity) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
        this.packInfo = packInfo;
        this.price = price;
        this.quantity = quantity;
        this.saveForLater = false; // Default to not saved for later
    }

    // --- Getters and Setters ---
    // You can generate these automatically in Android Studio (Right-click -> Generate -> Getter and Setter)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPackInfo() {
        return packInfo;
    }

    public void setPackInfo(String packInfo) {
        this.packInfo = packInfo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isSaveForLater() {
        return saveForLater;
    }

    public void setSaveForLater(boolean saveForLater) {
        this.saveForLater = saveForLater;
    }
}
