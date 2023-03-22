package com.example.plantscare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.plantscare.databinding.ActivitySectionsBinding;

public class Activity_sections extends AppCompatActivity {
    ActivitySectionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySectionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}