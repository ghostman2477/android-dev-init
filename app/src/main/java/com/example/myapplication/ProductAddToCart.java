package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ProductAddToCart implements Parcelable {

    private String productId;
    private String productName;
    private String productPrice;


    private int qtySelected;
    private String productImageUrl;
    private String brandName;
    private String flavourName;
    private String categoryName;

    public ProductAddToCart()
    {
        super();
    }
    public ProductAddToCart(String productId, String productName, String productPrice, int qtySelected, String productImageUrl, String brandName, String flavourName, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.qtySelected = qtySelected;
        this.productImageUrl = productImageUrl;
        this.brandName = brandName;
        this.flavourName = flavourName;
        this.categoryName = categoryName;
    }

    protected ProductAddToCart(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        productPrice = in.readString();
        qtySelected = in.readInt();
        productImageUrl = in.readString();
        brandName = in.readString();
        flavourName = in.readString();
        categoryName = in.readString();
    }

    public static final Creator<ProductAddToCart> CREATOR = new Creator<ProductAddToCart>() {
        @Override
        public ProductAddToCart createFromParcel(Parcel in) {
            return new ProductAddToCart(in);
        }

        @Override
        public ProductAddToCart[] newArray(int size) {
            return new ProductAddToCart[size];
        }
    };

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQtySelected() {
        return qtySelected;
    }

    public void setQtySelected(int qtySelected) {
        this.qtySelected = qtySelected;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getFlavourName() {
        return flavourName;
    }

    public void setFlavourName(String flavourName) {
        this.flavourName = flavourName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeString(productPrice);
        dest.writeInt(qtySelected);
        dest.writeString(productImageUrl);
        dest.writeString(brandName);
        dest.writeString(flavourName);
        dest.writeString(categoryName);
    }
}
