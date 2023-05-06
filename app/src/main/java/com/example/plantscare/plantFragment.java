package com.example.plantscare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.plantscare.database.the_plants;
import com.example.plantscare.databinding.FragmentPlantBinding;

import java.util.ArrayList;


public class plantFragment extends Fragment {

    RecyclerView rv;
    View view;
    ImageView imageView ;
    // ArrayList<Fragment> fragments;
    ArrayList<the_plants> plants;
    AdaptarPlant adapter;

    private static final String name = "plantName";
    private static final  String image= "imagePlant";
    private static final String ARG_image2 = "image2";



    // TODO: Rename and change types of parameters
    private String plantName;
    private String imagePlant;
    private String  image2;


    public plantFragment() {
        // Required empty public constructor
    }


    public static plantFragment newInstance(String plantName,String imagePlant) {
        plantFragment fragment = new plantFragment();
        Bundle args = new Bundle();
        args.putString(name, plantName);
        args.putString(image, imagePlant);


        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            plantName = getArguments().getString(name);
            imagePlant = getArguments().getString(image);
            image2 = getArguments().getString(ARG_image2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_plant, container, false);
        plants= new ArrayList<>();
        plants.add(new the_plants("خس",R.drawable.lettuce));
        plants.add(new the_plants("فاصوليا",R.drawable.beans));
        plants.add(new the_plants("بندورة",R.drawable.tometo));
        plants.add(new the_plants("خيار",R.drawable.option));
        plants.add(new the_plants("جزر",R.drawable.carrot));
        plants.add(new the_plants("كوسا",R.drawable.zucchini));
        plants.add(new the_plants("ملفوف ",R.drawable.cabbage));
        plants.add(new the_plants("بقدونس",R.drawable.parsley));



        // adapter=new Adapter(this,fragments);
        adapter= new AdaptarPlant(plants,getContext());
        // هنا ركبنا الادابتر ةعملنا تعبئة للبيانات في الريسايكل
        rv= view.findViewById(R.id.RV);
        rv.setAdapter(adapter);
        //rv.setLayoutManager(new GridLayoutManager(plantFragment.this));

        RecyclerView.LayoutManager LM=new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        rv.setLayoutManager(LM);
        adapter.notifyDataSetChanged();


        return view;
    }
}