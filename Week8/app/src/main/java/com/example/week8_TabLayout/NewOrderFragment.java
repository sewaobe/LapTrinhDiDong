package com.example.week8_TabLayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week8_TabLayout.databinding.FragmentNewOrderBinding;


public class NewOrderFragment extends Fragment {
    FragmentNewOrderBinding binding;

    public NewOrderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}