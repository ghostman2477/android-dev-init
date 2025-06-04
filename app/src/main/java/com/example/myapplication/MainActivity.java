package com.example.myapplication;// MainActivity.java
//package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.OnBackPressedCallback;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageButton hamburgerButton;
    private ToggleButton orderTypeToggleButton;
    private RecyclerView featuredProductsRecyclerView; //
    private RecyclerView categoriesRecyclerView;
    // Inside your MainActivity.java class

// Make sure these are declared at the class level (outside onCreate)
// private DrawerLayout drawerLayout;
// private NavigationView navigationView;
// private Toolbar toolbar;
// private ImageButton hamburgerButton;
// private ToggleButton orderTypeToggleButton; // ADD THIS DECLARATION
// private RecyclerView featuredProductsRecyclerView; // ADD THIS DECLARATION
// private RecyclerView categoriesRecyclerView; // ADD THIS DECLARATION


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        hamburgerButton = findViewById(R.id.hamburgerButton);
        ArrayList<Category> receivedItems = getIntent().getParcelableArrayListExtra("category_list");

        System.out.println("MainActivity"+ receivedItems.get(0).getCategoryName());



        // --- Setup Featured Products RecyclerView ---
        featuredProductsRecyclerView = findViewById(R.id.featuredProductsRecyclerView);
        featuredProductsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        List<Product> featuredProducts = new ArrayList<>();
        // Add sample products (replace with your actual product images/data)
        featuredProducts.add(new Product("Stylish Watch", "$120.00", R.drawable.placeholder_product_image));
        featuredProducts.add(new Product("Wireless Headphones", "$75.50", R.drawable.placeholder_product_image));
        featuredProducts.add(new Product("Smart Speaker", "$50.00", R.drawable.placeholder_product_image));
        featuredProducts.add(new Product("Designer Backpack", "$90.00", R.drawable.placeholder_product_image));
        ProductAdapter productAdapter = new ProductAdapter(featuredProducts);
        featuredProductsRecyclerView.setAdapter(productAdapter);


        // --- Setup Categories RecyclerView ---
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        // Assuming content_main.xml uses LinearLayoutManager for vertical scrolling or GridLayoutManager
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // If you want a grid, use: categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns for categories


        CategoryAdapter categoryAdapter = new CategoryAdapter(receivedItems);

        categoriesRecyclerView.setAdapter(categoryAdapter);


        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_categories) {
                // Your existing logic for toggling sub-menu visibility
                SubMenu subMenu = item.getSubMenu();
                if (subMenu != null) {
                    for (int i = 0; i < subMenu.size(); i++) {
                        MenuItem subItem = subMenu.getItem(i);
                        subItem.setVisible(!subItem.isVisible()); // Toggle visibility
                    }
                }
                Toast.makeText(this, "Categories Clicked", Toast.LENGTH_SHORT).show();
            } else if(id == R.id.po) { // This seems to be a specific item in your drawer_menu.xml
                Intent intent = new Intent(MainActivity.this, IceCreamShowList.class); // Assuming IceCreamShowList is an Activity
                startActivity(intent);
            } else if (id == R.id.nav_orders) {
                Toast.makeText(this, "Orders Clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_profile) {
                Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_settings) {
                Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_share) {
                Toast.makeText(this, "Share Clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_logout) {
                Toast.makeText(this, "Logout Clicked", Toast.LENGTH_SHORT).show();
            }
            drawerLayout.closeDrawer(navigationView);
            return true;
        });

        findViewById(R.id.homeButton).setOnClickListener(v -> Toast.makeText(this, "Home Button Clicked", Toast.LENGTH_SHORT).show());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Handle back button press using OnBackPressedDispatcher
        OnBackPressedCallback callback = new OnBackPressedCallback(false) { // Initially disabled
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(navigationView)) {
                    drawerLayout.closeDrawer(navigationView);
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);

        // Enable the callback only when the drawer is open
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                callback.setEnabled(slideOffset > 0);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                callback.setEnabled(true);
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                callback.setEnabled(false);
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                // Not needed for this specific case
            }
        });
    }

}