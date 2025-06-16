package com.example.myapplication;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class CustomerInfoActivity extends AppCompatActivity {

    private TextInputLayout nameInputLayout;
    private TextInputEditText nameEditText;
    private TextInputLayout addressInputLayout;
    private TextInputEditText addressEditText;
    private TextInputLayout phoneInputLayout;
    private TextInputEditText phoneEditText;
    private TextInputLayout cityInputLayout;
    private TextInputEditText cityEditText;
    private TextView stateTextView; // Hardcoded
    private TextInputLayout pinInputLayout;
    private TextInputEditText pinEditText;
    private Button proceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_customer_info);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.customer_info_main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- Initialize Toolbar ---
        Toolbar toolbar = findViewById(R.id.customerInfoToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Back arrow
            getSupportActionBar().setTitle("Customer Information");
        }

        // --- Initialize Views ---
        nameInputLayout = findViewById(R.id.nameInputLayout);
        nameEditText = findViewById(R.id.nameEditText);
        addressInputLayout = findViewById(R.id.addressInputLayout);
        addressEditText = findViewById(R.id.addressEditText);
        phoneInputLayout = findViewById(R.id.phoneInputLayout);
        phoneEditText = findViewById(R.id.phoneEditText);
        cityInputLayout = findViewById(R.id.cityInputLayout);
        cityEditText = findViewById(R.id.cityEditText);
        stateTextView = findViewById(R.id.stateTextView); // Displaying hardcoded value
        pinInputLayout = findViewById(R.id.pinInputLayout);
        pinEditText = findViewById(R.id.pinEditText);
        proceedButton = findViewById(R.id.proceedButton);

        // --- Add TextWatchers to clear errors when user types ---
        addTextWatcher(nameEditText, nameInputLayout);
        addTextWatcher(addressEditText, addressInputLayout);
        addTextWatcher(phoneEditText, phoneInputLayout);
        addTextWatcher(cityEditText, cityInputLayout);
        addTextWatcher(pinEditText, pinInputLayout);

        // --- Set OnClickListener for Proceed Button ---
        proceedButton.setOnClickListener(v -> {
            if (validateInputs()) {
                // All validations passed
                Toast.makeText(this, "Information saved. Proceeding...", Toast.LENGTH_SHORT).show();
                // TODO: Here, you would typically save this customer info (e.g., to shared preferences,
                // a local database, or directly pass it to the next activity like OrderSummaryActivity).
                // Example: Launch OrderSummaryActivity
                // Intent intent = new Intent(CustomerInfoActivity.this, OrderSummaryActivity.class);
                // // You can pass the collected data via intent extras if needed
                // intent.putExtra("customerName", Objects.requireNonNull(nameEditText.getText()).toString().trim());
                // // ... add other fields
                // startActivity(intent);
                // finish(); // Optional: finish this activity if user shouldn't return here easily
            } else {
                Toast.makeText(this, "Please correct the errors.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // --- Validation Logic ---
    private boolean validateInputs() {
        boolean isValid = true;

        // 1. Validate Name
        if (Objects.requireNonNull(nameEditText.getText()).toString().trim().isEmpty()) {
            nameInputLayout.setError("Name is required");
            isValid = false;
        } else {
            nameInputLayout.setError(null);
        }

        // 2. Validate Address
        if (Objects.requireNonNull(addressEditText.getText()).toString().trim().isEmpty()) {
            addressInputLayout.setError("Address is required");
            isValid = false;
        } else {
            addressInputLayout.setError(null);
        }

        // 3. Validate Phone Number
        String phoneNumber = Objects.requireNonNull(phoneEditText.getText()).toString().trim();
        if (phoneNumber.isEmpty()) {
            phoneInputLayout.setError("Phone number is required");
            isValid = false;
        } else if (phoneNumber.length() != 10) {
            phoneInputLayout.setError("Phone number must be 10 digits");
            isValid = false;
        } else {
            phoneInputLayout.setError(null);
        }

        // 4. Validate City
        if (Objects.requireNonNull(cityEditText.getText()).toString().trim().isEmpty()) {
            cityInputLayout.setError("City is required");
            isValid = false;
        } else {
            cityInputLayout.setError(null);
        }

        // 5. Validate Pin Code
        String pinCode = Objects.requireNonNull(pinEditText.getText()).toString().trim();
        if (pinCode.isEmpty()) {
            pinInputLayout.setError("Pin Code is required");
            isValid = false;
        } else if (pinCode.length() != 6) { // Common PIN code length in India
            pinInputLayout.setError("Pin Code must be 6 digits");
            isValid = false;
        } else {
            pinInputLayout.setError(null);
        }

        return isValid;
    }

    // Helper method to clear error messages when user starts typing
    private void addTextWatcher(TextInputEditText editText, TextInputLayout inputLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (inputLayout.getError() != null && s.length() > 0) {
                    inputLayout.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    // Handle back arrow in toolbar
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Go back to the previous activity (e.g., Cart or ProductList)
        return true;
    }
}