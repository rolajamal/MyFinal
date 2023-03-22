package com.example.plantscare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.plantscare.databinding.ActivityPage1Binding;
import com.example.plantscare.databinding.ActivityPage2Binding;

public class page2 extends AppCompatActivity {
    ActivityPage2Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPage2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnStr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), page3.class);
                startActivity(intent);
            }
            });
    }}