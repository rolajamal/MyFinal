package com.example.plantscare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.plantscare.databinding.ActivityAddDiseeasesBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Add_diseeases extends AppCompatActivity {
    ActivityAddDiseeasesBinding binding;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddDiseeasesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();
//        binding.btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                valideData();
//
//            }
//        });





    }
    String Diseases_name="";
    String Details="";
    String Diseases="";

    private void valideData() {

//        Diseases_name=binding.etNameD.getText().toString();
//        Details=binding.etDetails.getText().toString();
//        Diseases=binding.etDiseases.getText().toString();



        if(TextUtils.isEmpty(Diseases_name)){
            Toast.makeText(this, "please enter diseasesName", Toast.LENGTH_SHORT).show();
        }else {
            addDiseasesFirebase();
        }

        if(TextUtils.isEmpty(Details)){
            Toast.makeText(this, "please enter details", Toast.LENGTH_SHORT).show();
        }else {
            addDiseasesFirebase();
        }
        if(TextUtils.isEmpty(Diseases)){
            Toast.makeText(this, "please enter diseases", Toast.LENGTH_SHORT).show();
        }else {
            addDiseasesFirebase();
        }

    }

    private void addDiseasesFirebase() {


        long timestamp= System.currentTimeMillis();



        HashMap<String , Object> hashMap=new HashMap<>();
        hashMap.put("id",""+timestamp);
        hashMap.put("plant",Diseases_name);
        hashMap.put("details", Details);
        hashMap.put("diseases",Diseases);
        hashMap.put("timestamp",timestamp);
        hashMap.put("uid",firebaseAuth.getUid());


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("diseases");
        databaseReference.child(""+timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Add_diseeases.this, "diseases added sucssful....", Toast.LENGTH_SHORT).show();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Add_diseeases.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}