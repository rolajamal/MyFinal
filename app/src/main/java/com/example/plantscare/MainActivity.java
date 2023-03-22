package com.example.plantscare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.plantscare.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Animation splash_anim;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        splash_anim = AnimationUtils.loadAnimation(this, R.anim.splash);
        binding.animImg.setAnimation(splash_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this, page1.class));
                finish();
            }
        }, 5000);
    }
}