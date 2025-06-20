package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @GET("categories/fetch") // Replace with your actual API path
    Call<ResponseBody> makeRequest();
    @GET("product/fetch-product")
    Call<ResponseBody> fetchProduct(@Query("category") String category);

    @POST("/orders/create-order")
    Call<Void> saveOrders(@Body SaveOrder saveOrder);
}
