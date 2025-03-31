package com.example.week8_viewflipper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.week8_viewflipper.adapters.ImagesSliderViewAdapter;
import com.example.week8_viewflipper.adapters.ImagesViewAdapter;
import com.example.week8_viewflipper.models.Images;
import com.example.week8_viewflipper.models.ImagesSlider;
import com.example.week8_viewflipper.models.MessageModel;
import com.example.week8_viewflipper.network.RetrofitClient;
import com.example.week8_viewflipper.services.APIService;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiTap extends AppCompatActivity {
    APIService apiService;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<ImagesSlider> imagesList;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(viewPager.getCurrentItem()==imagesList.size()-1) {
                viewPager.setCurrentItem(0);
            }
            else {
                viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
            }
        }
    };
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_with_circle_indicator_and_view_pager);
        viewPager = findViewById(R.id.viewpage);
        circleIndicator = findViewById(R.id.circle_indicator);
        imagesList = getListImages();
        ImagesSliderViewAdapter adapter = new ImagesSliderViewAdapter(imagesList);
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
        handler.postDelayed (runnable, 3000);
//lắng nghe viewpager chuyển trang
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled (int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                handler.removeCallbacks (runnable);
                handler.postDelayed (runnable, 3000);

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

    }
    private List<ImagesSlider> getListImages(){
        final List<ImagesSlider>[] list = new List[]{new ArrayList<>()};
        apiService = RetrofitClient.getRetrofit().create(APIService.class);
        apiService.LoadImageSlider(1).enqueue(new Callback<MessageModel>() {
            @Override
            public void onResponse(Call<MessageModel> call, Response<MessageModel> response) {
                if(response.isSuccessful() && response.body() != null) {
                    list[0] = response.body().getResult();
                }
            }

            @Override
            public void onFailure(Call<MessageModel> call, Throwable t) {
                Toast.makeText(BaiTap.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
        return list[0];
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
}