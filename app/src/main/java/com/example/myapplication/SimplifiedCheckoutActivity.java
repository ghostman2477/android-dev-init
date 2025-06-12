package com.example.myapplication; // Adjust your package name

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.CheckoutProductAdapter; // Reusing this adapter
import com.example.myapplication.CartItem; // Reusing this model

import java.util.ArrayList;
import java.util.List;

public class SimplifiedCheckoutActivity extends AppCompatActivity {

    private RecyclerView simplifiedCartItemsRecyclerView;
    private CheckoutProductAdapter checkoutProductAdapter;
    private List<CartItem> cartItemList; // This will hold your items from the cart

    private TextView simplifiedTotalPriceTextView;
    private Button simplifiedPlaceOrderButton;
    private ImageView simplifiedCartIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_simplified_checkout);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.simplified_checkout_main_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // --- Initialize Toolbar ---
        Toolbar toolbar = findViewById(R.id.simplifiedCheckoutToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Back arrow
            getSupportActionBar().setTitle("Your Order"); // Set toolbar title
        }

        // Handle toolbar icon
        simplifiedCartIcon = findViewById(R.id.simplifiedCartIcon);
        simplifiedCartIcon.setOnClickListener(v -> Toast.makeText(this, "Cart icon clicked!", Toast.LENGTH_SHORT).show());


        // --- Initialize Views ---
        simplifiedTotalPriceTextView = findViewById(R.id.simplifiedTotalPriceTextView);
        simplifiedPlaceOrderButton = findViewById(R.id.simplifiedPlaceOrderButton);


        // --- Setup Cart Items RecyclerView ---
        simplifiedCartItemsRecyclerView = findViewById(R.id.simplifiedCartItemsRecyclerView);
        cartItemList = new ArrayList<>();
        addSampleCartItems(); // Populate with dummy data for now (will be dynamic later)

        simplifiedCartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkoutProductAdapter = new CheckoutProductAdapter(this, cartItemList);
        simplifiedCartItemsRecyclerView.setAdapter(checkoutProductAdapter);


        // --- Set Event Listeners ---
        updateTotalPrice(); // Calculate and display initial total price

        simplifiedPlaceOrderButton.setOnClickListener(v -> {
            Toast.makeText(this, "Placing Order! Total: " + simplifiedTotalPriceTextView.getText(), Toast.LENGTH_LONG).show();
            // TODO: In a real app, you would send this order data to your Spring Boot backend
            // After successful order, navigate to an Order Confirmation/Success page
        });
    }

    // --- Helper to add dummy cart items (will come dynamically from your cart logic) ---
    private void addSampleCartItems() {
        // These items would typically be passed from your previous activity (the product list)
        // or fetched from a local cart database/preference.
        cartItemList.add(new CartItem(
                "item1",
                "https://m.media-amazon.com/images/I/41K0pZq9+CL._AC_SY200_.jpg", // Durex image
                "Durex Extra Time Longer Lasting Condom",
                "1 pack (3 pieces)",
                90.00, // Price for 1 quantity
                1 // Quantity
        ));
        cartItemList.add(new CartItem(
                "item2",
                "https://cdn.pixabay.com/photo/2018/03/23/07/26/ice-cream-3253018_1280.jpg", // Ice Cream image
                "Classic Vanilla Ice Cream",
                "1 cup (100g)",
                50.00,
                2
        ));
        cartItemList.add(new CartItem(
                "item3",
                "https://images-cdn.ubuy.co.in/6344d32049d5a711440f3536-hershey-s-kisses-milk-chocolate-candy.jpg", // Chocolate image
                "Hershey's Kisses Milk Chocolate",
                "1 bag (150g)",
                150.00,
                1
        ));
    }

    // Helper to update total price based on cart items
    private void updateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItemList) {
            total += item.getPrice() * item.getQuantity(); // Assuming price is per unit
        }
        simplifiedTotalPriceTextView.setText(String.format("₹%.0f", total));
    }

    // Handle back arrow in toolbar
    @Override
    public boolean onSupportNavigateUp() {
        finish(); // Go back to the previous activity (e.g., ProductListActivity)
        return true;
    }
}