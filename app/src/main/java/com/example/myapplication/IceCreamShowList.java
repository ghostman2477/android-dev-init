package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class IceCreamShowList extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton hamburgerButton;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ice_cream_show_list);
        hamburgerButton = findViewById(R.id.hamburgerButton);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));

    }
}