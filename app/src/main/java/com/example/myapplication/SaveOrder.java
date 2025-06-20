package com.example.myapplication;

import java.util.List;

public class SaveOrder {

    private List<ProductOrderRequest> products;
    private String phone;
    private String name;
    private String addressfirstLine;
    private String city;
    private String state;
    private String pincode;
    private String orderStatus;


    public static class ProductOrderRequest {
        private String productId;

        public ProductOrderRequest(String productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        private int quantity;

        public String getProductId() { return productId; }
        public void setProductId(String productId) { this.productId = productId; }

        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public List<ProductOrderRequest> getProducts() { return products; }
    public void setProducts(List<ProductOrderRequest> products) { this.products = products; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddressfirstLine() { return addressfirstLine; }
    public void setAddressfirstLine(String addressfirstLine) { this.addressfirstLine = addressfirstLine; }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

}
