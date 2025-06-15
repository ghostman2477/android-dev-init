package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ProductSummary extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton hamburgerButton;
    private NavigationView navigationView;
    private RecyclerView productsRecyclerView;

    private ProductSummaryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productsummary);
        hamburgerButton = findViewById(R.id.hamburgerButton);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));
        productsRecyclerView = findViewById(R.id.featuredProductsRecyclerView);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        System.out.println("Ayan before");

       ArrayList<ProductAddToCart> productList = new ArrayList<>();
        productList = getIntent().getParcelableArrayListExtra("productAddToCarts");
//        productList.add(new Product("Laptop", "₹50,000", 1,2,3,"","","",""));
//        productList.add(new Product("Laptop", "₹50,000", 1,2,3,"","","",""));
//        productList.add(new Product("Laptop", "₹50,000", 1,2,3,"","","",""));
        System.out.println("Ayan find size: "+productList.size());
        adapter = new ProductSummaryAdapter(productList);
        productsRecyclerView.setAdapter(adapter);
    }
}