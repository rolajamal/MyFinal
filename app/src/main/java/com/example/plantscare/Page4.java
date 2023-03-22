package com.example.plantscare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.plantscare.databinding.ActivityPage3Binding;
import com.example.plantscare.databinding.ActivityPage4Binding;

public class Page4 extends AppCompatActivity {
    ActivityPage4Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityPage4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Signup.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Signin.class);
                startActivity(intent);
                finish();
            }
        });
    }
}