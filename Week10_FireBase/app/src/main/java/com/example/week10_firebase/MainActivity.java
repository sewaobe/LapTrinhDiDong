package com.example.week10_firebase;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int PICK_VIDEO_REQUEST = 100;
    private Uri videoUri;
    private VideoView videoPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnChoose  = findViewById(R.id.btnChoose);
        Button btnUpload  = findViewById(R.id.btnUpload);
        videoPreview      = findViewById(R.id.videoPreview);

        // Kéo vào nút play/pause
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoPreview);
        videoPreview.setMediaController(mc);

        btnChoose.setOnClickListener(v -> chooseVideo());
        btnUpload.setOnClickListener(v -> {
            if (videoUri != null) uploadVideoToCloudinary(videoUri);
            else Toast.makeText(this, "Chọn video trước!", Toast.LENGTH_SHORT).show();
        });
    }

    private void chooseVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        intent.setType("video/*");
        startActivityForResult(intent, PICK_VIDEO_REQUEST);
    }

    @Override
    protected void onActivityResult(int req, int res, @Nullable Intent data) {
        super.onActivityResult(req, res, data);
        if (req == PICK_VIDEO_REQUEST && res == RESULT_OK && data != null) {
            videoUri = data.getData();
            videoPreview.setVideoURI(videoUri);
            videoPreview.setVisibility(View.VISIBLE);
            videoPreview.start();
        }
    }


    private void uploadVideoToCloudinary(Uri uri) {
        File file = FileUtils.getFile(this, uri); // dùng FileUtils ở dưới

        RequestBody requestFile = RequestBody.create(MediaType.parse("video/*"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        RequestBody uploadPreset = RequestBody.create(MultipartBody.FORM, "video_unsigned");
        // Lấy email user từ Firebase
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        String sanitizedEmail = email.replaceAll("[^a-zA-Z0-9]", "_");
        String folderPath = "videos/" + sanitizedEmail;
        RequestBody folder = RequestBody.create(MultipartBody.FORM, folderPath);

        String videoName = "video_" + System.currentTimeMillis();
        RequestBody publicId = RequestBody.create(MultipartBody.FORM, videoName);

        CloudinaryServiceAPI service = ApiClient.getClient().create(CloudinaryServiceAPI.class);
        service.uploadVideo(body, uploadPreset, folder, publicId).enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if (response.isSuccessful()) {
                    String message = "Upload thành công! URL: " + response.body().getUrl();
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Upload thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}