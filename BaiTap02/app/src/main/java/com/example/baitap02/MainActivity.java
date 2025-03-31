package com.example.baitap02;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout bg = (ConstraintLayout) findViewById(R.id.main);
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(R.drawable.background_intro);
        arrayList.add(R.drawable.cat_1);
        arrayList.add(R.drawable.cat_2);
        arrayList.add(R.drawable.btn1);
        Random random = new Random();
        int vitri = random.nextInt(arrayList.size());
        bg.setBackgroundResource(arrayList.get(vitri));

        Switch sw = (Switch) findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int vitri1 = random.nextInt(arrayList.size());
                bg.setBackgroundResource(arrayList.get(vitri1));
            }
        });

    }
}