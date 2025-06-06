package com.example.myapplication;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("categories/fetch") // Replace with your actual API path
    Call<ResponseBody> makeRequest();
}
