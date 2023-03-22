package com.example.plantscare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.plantscare.databinding.ActivityMainBinding;
import com.example.plantscare.databinding.ActivityPage1Binding;

public class page1 extends AppCompatActivity {
    ActivityPage1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPage1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnStr1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),page2.class);
                startActivity(intent);
            }
        });


    }
}