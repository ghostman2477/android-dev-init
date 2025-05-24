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

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageButton hamburgerButton;


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

        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_categories) {
                Toast.makeText(this, "Categories Clicked", Toast.LENGTH_SHORT).show();
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