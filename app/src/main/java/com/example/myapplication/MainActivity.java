package com.example.myapplication;// MainActivity.java
//package com.example.ecommerceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
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
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

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
        navigationView = findViewById(R.id.naView);
        hamburgerButton = findViewById(R.id.hamburgerButton);
        navigationView.setClickable(true);
        navigationView.setFocusable(true);
        navigationView.bringToFront(); // Even though it appears visible
        navigationView.requestLayout();
        navigationView.invalidate();
        ArrayList<Category> receivedItems = getIntent().getParcelableArrayListExtra("category_list");


        System.out.println("MainActivity"+ receivedItems.get(0).getCategoryName());



        // --- Setup Featured Products RecyclerView ---
        featuredProductsRecyclerView = findViewById(R.id.featuredProductsRecyclerView);
        featuredProductsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        // --- Setup Categories RecyclerView ---
        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        // Assuming content_main.xml uses LinearLayoutManager for vertical scrolling or GridLayoutManager
        categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        int spacingInPixels = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

        categoriesRecyclerView.addItemDecoration(
                new GridSpacingItemDecoration(2, spacingInPixels, true));

        // If you want a grid, use: categoriesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns for categories


        CategoryAdapter categoryAdapter = new CategoryAdapter(receivedItems);

        categoriesRecyclerView.setAdapter(categoryAdapter);

      //  navigationView.inflateMenu(R.menu.drawer_menu);
        hamburgerButton.setOnClickListener(v ->drawerLayout.openDrawer(GravityCompat.START));


        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.nav_home){
                Toast.makeText(MainActivity.this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, IceCreamActivity.class);
                startActivity(intent);
            }
            else if(id == R.id.nav_orders ){
                Toast.makeText(MainActivity.this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, TrackOrder_mbl_no.class);
                startActivity(intent);
            }
            return true;
        });


        // findViewById(R.id.homeButton).setOnClickListener(v -> Toast.makeText(this, "Home Button Clicked", Toast.LENGTH_SHORT).show());

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