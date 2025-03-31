package com.example.week8_viewflipper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.week8_viewflipper.adapters.ImagesViewAdapter;
import com.example.week8_viewflipper.models.Images;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class SlideWithCircleIndicatorAndViewPager extends AppCompatActivity {

    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private List<Images> imagesList;
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
        ImagesViewAdapter adapter = new ImagesViewAdapter(imagesList);
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
    private List<Images> getListImages(){
        List<Images> list = new ArrayList<>();
        list.add(new Images (R.drawable.quangcao));
        list.add(new Images (R.drawable.coffee));
        list.add(new Images (R.drawable.companypizza));
        list.add(new Images (R.drawable.themoingon));
        return list;


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