package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class TrackOrder_mbl_no extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageButton hamburgerButton;
    private ToggleButton orderTypeToggleButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_track_order_mbl_no);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.naView);
        hamburgerButton = findViewById(R.id.hamburgerButton);
        navigationView.setClickable(true);
        navigationView.setFocusable(true);
        navigationView.bringToFront(); // Even though it appears visible
        navigationView.requestLayout();
        navigationView.invalidate();
        hamburgerButton.setOnClickListener(v ->drawerLayout.openDrawer(GravityCompat.START));
        super.onCreate(savedInstanceState);

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}