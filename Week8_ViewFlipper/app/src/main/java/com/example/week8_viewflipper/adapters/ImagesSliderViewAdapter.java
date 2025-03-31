package com.example.week8_viewflipper.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.week8_viewflipper.R;
import com.example.week8_viewflipper.models.Images;
import com.example.week8_viewflipper.models.ImagesSlider;

import java.util.List;

public class ImagesSliderViewAdapter extends PagerAdapter {
    private List<ImagesSlider> imagesList;

    public ImagesSliderViewAdapter(List<ImagesSlider> imagesList) {
        this.imagesList = imagesList;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_images,container, false);
        ImageView imageView = view.findViewById(R.id.imgView);
        ImagesSlider images = imagesList.get(position);
        imageView.setImageResource(images.getId());
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return imagesList==null ? 0: imagesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
