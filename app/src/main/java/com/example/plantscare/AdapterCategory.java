package com.example.plantscare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.plantscare.database.Categories;
import com.example.plantscare.databinding.RowCategoryBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.HolderCategory> {
    ArrayList<Categories> categoriesArrayList;

    public AdapterCategory(ArrayList<Categories> categoriesArrayList) {
        this.categoriesArrayList = categoriesArrayList;
    }

    @NonNull
    @Override
    public HolderCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowCategoryBinding  binding=RowCategoryBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new HolderCategory(binding);
    }

    @Override
    public int getItemCount() {
        return categoriesArrayList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull HolderCategory holder, int position) {
        int pos =position;

        Categories modelCategory=categoriesArrayList.get(pos);

        String id=modelCategory.getId();
        String category=modelCategory.getName();

        holder.Category_title.setText(category);
         //لما بدو يضغط علي زر الحدف
        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete").setMessage("are you sure you want to delete this category")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                deleteCategory(modelCategory,holder);

                            }
                        }).setNegativeButton("Cansel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();

            }
        });
    }

    private void deleteCategory(Categories modelCategory, HolderCategory holder) {

        String id=modelCategory.getId();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("category");
        databaseReference.child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }




    class HolderCategory extends RecyclerView.ViewHolder{

        TextView Category_title;
        ImageButton delete_icon;

        public HolderCategory(RowCategoryBinding binding) {
            super(binding.getRoot());


            Category_title= binding.categoryTitle;;
            delete_icon= binding.deleteIcon;
        }
    }
}
