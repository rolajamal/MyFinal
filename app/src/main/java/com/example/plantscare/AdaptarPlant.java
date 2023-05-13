package com.example.plantscare;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantscare.database.the_plants;
import com.example.plantscare.databinding.ItemBinding;


import java.util.ArrayList;
import java.util.List;


public class AdaptarPlant extends RecyclerView.Adapter<AdaptarPlant.PlantHoldar> implements Filterable {
    ArrayList<the_plants> plants;
    ArrayList<the_plants> plantslist;

    Context context;

    public AdaptarPlant(ArrayList<the_plants> plants, Context context) {
        this.plants = plants;
        this.context = context;
        plantslist=new ArrayList<>(plants);
    }

    //MyListener listener;
    @NonNull
    @Override
    public PlantHoldar onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       ItemBinding binding=ItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PlantHoldar(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantHoldar holder, int position) {

        int pos=position;
        the_plants pln=plants.get(pos);
        holder.Name.setText(pln.getName());
        holder.imagePlants.setImageResource(pln.getImga());



    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    @Override
    public Filter getFilter() {
        return resFilter;
    }

    private Filter resFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<the_plants> filterList=new ArrayList<>();
            if(charSequence==null||charSequence.length()==0){
                filterList.addAll(plantslist);
            }else {
                String filterPattern=charSequence.toString().toLowerCase().trim();
                for (the_plants plant : plantslist){
                    if (plant.getName().toLowerCase().contains(filterPattern)){
                        filterList.add(plant);

                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            plants.clear();
            plants.addAll((ArrayList)filterResults.values);
            notifyDataSetChanged();

        }
    };

    class PlantHoldar extends RecyclerView.ViewHolder{
        TextView Name;
        ImageView imagePlants;


        public PlantHoldar(@NonNull ItemBinding binding) {
            super(binding.getRoot());
            Name=binding.tvName;
            imagePlants=binding.imgPlant;



        }
    }
}
