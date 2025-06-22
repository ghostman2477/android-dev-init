package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class CurrentlyInCart {
    private static final CurrentlyInCart instance = new CurrentlyInCart();
    private List<ProductAddToCart> sharedList = new ArrayList<>();

    private CurrentlyInCart() {}

    public static CurrentlyInCart getInstance() {
        return instance;
    }

    public List<ProductAddToCart> getSharedList() {
        return sharedList;
    }
}

