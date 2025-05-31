package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class IceCreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_cream); // This line sets the layout for this Activity

        // Get references to the buttons from your layout
        Button individualOrderButton = findViewById(R.id.individualOrderButton);
        Button partyOrderButton = findViewById(R.id.partyOrderButton);

        // Set click listener for the "Place Individual Order" button
        individualOrderButton.setOnClickListener(v -> {
            Toast.makeText(IceCreamActivity.this, "Individual Order Placed!", Toast.LENGTH_SHORT).show();
            // TODO: Add logic here to proceed with individual order (e.g., start a new activity)
            // Example: If you want to go to MainActivity (your e-commerce app) after this
            // Intent intent = new Intent(IceCreamActivity.this, MainActivity.class);
            // startActivity(intent);
            // finish(); // Optional: finish this activity so user can't go back to it
        });

        // Set click listener for the "Place Party Order" button
        partyOrderButton.setOnClickListener(v -> {
            Toast.makeText(IceCreamActivity.this, "Party Order Placed!", Toast.LENGTH_SHORT).show();
            // TODO: Add logic here to proceed with party order (e.g., start a new activity)
        });
    }
}