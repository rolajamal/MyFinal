package com.example.plantscare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import com.example.plantscare.databinding.ActivityCropsBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class crops extends AppCompatActivity {
    ActivityCropsBinding binding;
    AdaptarPlant adaptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCropsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Winters");
        tabs.add("Autumn");
        tabs.add("Summer");
        tabs.add("Spring");
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(plantFragment.newInstance("خس", String.valueOf(R.drawable.cabbage)));
        fragments.add(plantFragment.newInstance("غراب بين", String.valueOf(R.drawable.carrot)));
        fragments.add(plantFragment.newInstance("خس", String.valueOf(R.drawable.cabbage)));
        fragments.add(plantFragment.newInstance("غراب بين", String.valueOf(R.drawable.carrot)));

        AdaptarFragment adapter = new AdaptarFragment(this,fragments);
        binding.VB2.setAdapter(adapter);

        new TabLayoutMediator(binding.TL, binding.VB2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs.get(position)) ;
            }
        }).attach();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_drawer,menu);

        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) search.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adaptar.getFilter().filter(s);
                return false;
            }
        });

        return true;
    }

}
