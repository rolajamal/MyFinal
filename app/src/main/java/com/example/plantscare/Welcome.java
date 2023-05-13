package com.example.plantscare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.plantscare.databinding.ActivityWelcomeBinding;
import com.google.android.material.navigation.NavigationView;

public class Welcome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    ActivityWelcomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setSupportActionBar(binding.tool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);
        binding.navigation.setNavigationItemSelectedListener(this);






    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Toast.makeText(this, item.getTitle()+"cliked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.navigation:
                Toast.makeText(this, item.getTitle()+"cliked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.profile:
                Toast.makeText(this, item.getTitle()+"cliked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.payment:
                Toast.makeText(this, item.getTitle()+"cliked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.support:
                Toast.makeText(this, item.getTitle()+"cliked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.about:
                Toast.makeText(this, item.getTitle()+"cliked", Toast.LENGTH_SHORT).show();
                break;
        }

        binding.drawar.closeDrawer(GravityCompat.START);
        
        
        
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
               binding.drawar.openDrawer(GravityCompat.START);
               break;
        }
        return super.onOptionsItemSelected(item);
    }
}