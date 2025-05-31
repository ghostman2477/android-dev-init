package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class IceCreamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ice_cream); // This line sets the layout for this Activity

        Button retailButton = findViewById(R.id.individualOrderButton); // Get the button instance
        Button poButton = findViewById(R.id.partyOrderButton); // Get the button instance

// Set a click listener
        retailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click event
                Intent intent = new Intent(IceCreamActivity.this, MainActivity.class);
                startActivity(intent); // Move to another screen
            }
        });

        poButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the button click event
                Intent intent = new Intent(IceCreamActivity.this, MainActivity.class);
                startActivity(intent); // Move to another screen
            }
        });

    }

}