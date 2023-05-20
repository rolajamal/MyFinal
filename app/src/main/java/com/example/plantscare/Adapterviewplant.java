package com.example.plantscare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.plantscare.database.Theplant;

import com.example.plantscare.databinding.ViewplantitemBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adapterviewplant  extends RecyclerView.Adapter<Adapterviewplant .HolderPlant>  {
    ArrayList<Theplant> theplantArrayList ;
   onclick onclick;

    public Adapterviewplant(ArrayList<Theplant> theplantArrayList, onclick onclick) {
        this.theplantArrayList = theplantArrayList;
        this.onclick= onclick;
    }


    @NonNull
    @Override
    public Adapterviewplant.HolderPlant onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
   ViewplantitemBinding binding=ViewplantitemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Adapterviewplant.HolderPlant(binding);
    }

    @Override
    public int getItemCount() {
        return theplantArrayList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull Adapterviewplant.HolderPlant holder, int position) {
        int pos = position;


       Theplant modelplant=theplantArrayList.get(pos);

        String id=   modelplant.getId();
//        modelplant.setId(id);

//        String nameplant = modelplant.getPlant();
//          String name =  holder.tv_name.getText().toString();

        holder.tv_name.setText(modelplant.getPlant());

        //لما بدو يضغط علي زر الحدف
        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                builder.setTitle("Delete").setMessage("are you sure you want to delete this plant")
                        .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                deletePlant(modelplant,holder);


                            }
                        }).setNegativeButton("Cansel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();

            }
        });
        holder.edit_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = modelplant.getId();
                String name = modelplant.getPlant();
                onclick.onclick(id);


            }
        });
    }

    private void deletePlant(  Theplant  modelplant, Adapterviewplant.HolderPlant holder) {

         String id= modelplant.getId();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("plant");
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




    class HolderPlant extends RecyclerView.ViewHolder{

        TextView tv_name;
        Button delete_icon,edit_icon;

        public HolderPlant(ViewplantitemBinding binding) {
            super(binding.getRoot());

            tv_name = binding.tvName;
//            Category_title= binding.categoryTitle;;
            delete_icon= binding.btnDelete;
            edit_icon=binding.btnEdit;
        }
    }
}
