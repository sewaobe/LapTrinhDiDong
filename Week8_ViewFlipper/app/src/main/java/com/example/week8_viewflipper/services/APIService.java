package com.example.week8_viewflipper.services;

import com.example.week8_viewflipper.models.MessageModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    @FormUrlEncoded
    @POST("newimagesmanager.php")
    Call<MessageModel> LoadImageSlider(@Field("position") int position);
}
