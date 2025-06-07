package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class IceCreamShowList extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton hamburgerButton;
    private NavigationView navigationView;
    private RecyclerView productsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ice_cream_show_list);
        hamburgerButton = findViewById(R.id.hamburgerButton);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);


        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));
        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        productsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        List<Product> categorisedProducts = new ArrayList<>();
        // Add sample products (replace with your actual product images/data)
        categorisedProducts.add(new Product("Stylish Watch", "$120.00", R.drawable.placeholder_product_image,1));
        categorisedProducts.add(new Product("Stylish Watch", "$75.50", R.drawable.placeholder_product_image,1));
        categorisedProducts.add(new Product("Stylish Watch", "$50.00", R.drawable.placeholder_product_image,1));
        categorisedProducts.add(new Product("Stylish Watch", "$90.00", R.drawable.placeholder_product_image,1));
        ProductAdapter productAdapter = new ProductAdapter(categorisedProducts);
        productsRecyclerView.setAdapter(productAdapter);




    }
}