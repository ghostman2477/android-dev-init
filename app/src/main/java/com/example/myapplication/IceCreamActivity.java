package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

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

public class IceCreamActivity extends AppCompatActivity {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:7017/") // Base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService apiService = retrofit.create(ApiService.class);
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

                apiService.makeRequest().enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                //System.out.println(response.body().string());
                                String jsonString = response.body().string(); // Convert ResponseBody to String
                                Gson gson = new Gson();
                                Type listType = new TypeToken<ArrayList<Category>>() {}.getType();
                                ArrayList<Category> categories = new Gson().fromJson(jsonString, listType);
                                for(int i=0;i<categories.size();i++){
                                    System.out.println(categories.get(i).getCategoryName());
                                }

                                System.out.println(categories);
                                Log.d("Response", response.body().string());

                                // Handle the button click event
                                Intent intent = new Intent(IceCreamActivity.this, MainActivity.class);
                                intent.putParcelableArrayListExtra("category_list", categories);
                                startActivity(intent); // Move to another screen
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