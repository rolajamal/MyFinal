package com.example.plantscare;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.plantscare.databinding.ActivityAddPlantBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Add_plant extends AppCompatActivity {
    ActivityAddPlantBinding binding;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPlantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valideData();

            }
        });




    }
    String Plant_name="";
    String Details="";
    String Diseases="";
    String Category="";

    private void valideData() {

        Plant_name=binding.etNameP.getText().toString();
        Details=binding.etDetails.getText().toString();


        if(TextUtils.isEmpty(Plant_name)){
            Toast.makeText(this, "please enter plant", Toast.LENGTH_SHORT).show();
        }else {
            addPlantFirebase();
        }

        if(TextUtils.isEmpty(Details)){
            Toast.makeText(this, "please enter details", Toast.LENGTH_SHORT).show();
        }else {
            addPlantFirebase();
        }

    }

    private void addPlantFirebase() {


        long timestamp= System.currentTimeMillis();



        HashMap<String , Object> hashMap=new HashMap<>();
        hashMap.put("id",""+timestamp);
        hashMap.put("plant",Plant_name);
        hashMap.put("details", Details);
        hashMap.put("diseases",Diseases);
        hashMap.put("category",Category);
        hashMap.put("timestamp",timestamp);
        hashMap.put("uid",firebaseAuth.getUid());


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("plant");
        databaseReference.child(""+timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Add_plant.this, "plant added sucssful....", Toast.LENGTH_SHORT).show();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Add_plant.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }






}