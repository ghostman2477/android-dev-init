package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ProductSummary extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton hamburgerButton;
    private NavigationView navigationView;
    private RecyclerView productsRecyclerView;

    private Button button;

    private ProductSummaryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_productsummary);
        hamburgerButton = findViewById(R.id.hamburgerButton);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        button = findViewById(R.id.Order);
        navigationView.setClickable(true);
        navigationView.setFocusable(true);
        navigationView.bringToFront(); // Even though it appears visible
        navigationView.requestLayout();
        navigationView.invalidate();
        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));
        productsRecyclerView = findViewById(R.id.featuredProductsRecyclerView);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.nav_home){
                Toast.makeText(this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, IceCreamActivity.class);
                startActivity(intent);
            }
            else if(id == R.id.nav_orders ){
                Toast.makeText(this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, TrackOrder_mbl_no.class);
                startActivity(intent);
            }
            return true;
        });
      final ArrayList<ProductAddToCart> productList = (ArrayList<ProductAddToCart>) CurrentlyInCart.getInstance().getSharedList();

        adapter = new ProductSummaryAdapter(productList);
        productsRecyclerView.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(ProductSummary.this, CustomerInfoActivity.class);
                startActivity(intent);
            }
        });
    }
}