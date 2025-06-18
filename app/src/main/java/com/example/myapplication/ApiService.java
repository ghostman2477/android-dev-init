package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("categories/fetch") // Replace with your actual API path
    Call<ResponseBody> makeRequest();
    @GET("product/fetch-product")
    Call<ResponseBody> fetchProduct(@Query("category") String category);
}
