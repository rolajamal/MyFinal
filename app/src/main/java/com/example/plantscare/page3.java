package com.example.plantscare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.plantscare.databinding.ActivityPage2Binding;
import com.example.plantscare.databinding.ActivityPage3Binding;

public class page3 extends AppCompatActivity {
    ActivityPage3Binding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPage3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Page4.class);
                startActivity(intent);
            }
        });
    }
}