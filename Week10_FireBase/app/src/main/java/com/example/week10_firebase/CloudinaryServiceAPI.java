package com.example.week10_firebase;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface CloudinaryServiceAPI {
    @Multipart
    @POST("video/upload")
    Call<UploadResponse> uploadVideo(
            @Part MultipartBody.Part file,
            @Part("upload_preset") RequestBody uploadPreset,
            @Part("folder") RequestBody folder,
            @Part("public_id") RequestBody publicId
    );
}
