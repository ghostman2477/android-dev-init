package com.example.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Category implements Parcelable {
    @SerializedName("id")
     String categoryId;

    @SerializedName("categoryName")
     String categoryName;

    @SerializedName("categoryImageURL")
    String categoryIconUrl; // Using int for drawable resource ID

    public Category() {} // Add this if missing

    public Category(String categoryId, String categoryName, String categoryIconUrl) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryIconUrl = categoryIconUrl;
    }

    protected Category(Parcel in) {
        categoryId = in.readString();
        categoryName = in.readString();
        categoryIconUrl = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryIconUrl() {
        return categoryIconUrl;
    }

    public void setCategoryIconUrl(String categoryIconUrl) {
        this.categoryIconUrl = categoryIconUrl;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(categoryId);
        dest.writeString(categoryName);
        dest.writeString(categoryIconUrl);
    }
}
