package com.example.retrofitasses.Network;

import com.example.retrofitasses.Model.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataService {
    @GET("posts.json")
    Call<Data> getAllData();
}
