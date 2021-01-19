package com.example.tasteofhome.api_interfaces;


import com.example.tasteofhome.ContentRecipeAdd;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("getdata")
    Call<List<ContentRecipeAdd>> getPosts();

    @POST("senddata")
    Call<ContentRecipeAdd> addData(@Body ContentRecipeAdd post);
}
