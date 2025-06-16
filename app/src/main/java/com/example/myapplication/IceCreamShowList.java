package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IceCreamShowList extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ImageButton hamburgerButton;
    private NavigationView navigationView;
    private RecyclerView productsRecyclerView;

    private ExtendedFloatingActionButton fab;
    ArrayList<Product> products = new ArrayList<>();

    private ArrayList<ProductAddToCart> productAddToCarts = new ArrayList<>();
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:7017/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    ApiService apiService = retrofit.create(ApiService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ice_cream_show_list);
        hamburgerButton = findViewById(R.id.hamburgerButton);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);
        fab = findViewById(R.id.fab);
        hamburgerButton.setOnClickListener(v -> drawerLayout.openDrawer(navigationView));
        productsRecyclerView = findViewById(R.id.productsRecyclerView);
        productsRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        String category = getIntent().getStringExtra("category_name");
        navigationView.setClickable(true);
        navigationView.setFocusable(true);
        navigationView.bringToFront(); // Even though it appears visible
        navigationView.requestLayout();
        navigationView.invalidate();
        List<Product> categorisedProducts = new ArrayList<>();
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if(id == R.id.nav_home){
                Toast.makeText(this, "Clicked: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, IceCreamActivity.class);
                startActivity(intent);
            }
            return true;
        });

        apiService.fetchProduct(category).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String jsonString = response.body().string(); // Convert ResponseBody to String
                        Gson gson = new Gson();
                        Type listType = new TypeToken<ArrayList<Product>>() {}.getType();
                        products = new Gson().fromJson(jsonString, listType);
                        for(int i=0;i<products.size();i++){
                           System.out.println("OK"+products.get(i).getProductName());
                        }
                        ProductAdapter productAdapter = new ProductAdapter(productAddToCarts,products,fab);
                        productsRecyclerView.setAdapter(productAdapter);
                        Log.d("Response", response.body().string());


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Your action here
                for(int i=0;i<productAddToCarts.size();i++){
                    System.out.println(productAddToCarts.get(i).getProductName()+productAddToCarts.get(i).getProductId()
                    +productAddToCarts.get(i).getQtySelected());
                }
              Intent intent = new Intent(view.getContext(), ProductSummary.class);
              intent.putParcelableArrayListExtra("productAddToCarts", productAddToCarts);
              view.getContext().startActivity(intent);
            }
        });
    }
}