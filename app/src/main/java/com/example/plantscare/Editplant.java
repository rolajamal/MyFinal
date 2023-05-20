package com.example.plantscare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.plantscare.database.Theplant;
import com.example.plantscare.databinding.ActivityEditplantBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Editplant extends AppCompatActivity {
    ActivityEditplantBinding binding;
    String id;
    ArrayList<String> theplantArrayList;
    String plant = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityEditplantBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        id = getIntent().getStringExtra("id");
       plant = getIntent().getStringExtra("plant");
//        Log.d("name", name.toString());
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        loadplant();
        loadeditinfo();
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();

            }
        });
    }

    private void validateData() {
        plant= binding.etNameP.getText().toString().trim();
        if(TextUtils.isEmpty(plant)) {
            Toast.makeText(this, "please enter plant", Toast.LENGTH_SHORT).show();

        }else if (TextUtils.isEmpty(id)){
            Toast.makeText(this, "please enter plantid", Toast.LENGTH_SHORT).show();

        }else {
            updateplant();
        }
    }

    private void updateplant() {
        HashMap<String , Object> hashMap=new HashMap<>();
        hashMap.put("id",""+id);
        hashMap.put("plant",""+ plant);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("plant");
        databaseReference.child(id).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Editplant.this, "plant info update", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Toast.makeText(Editplant.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadeditinfo() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("plant");
        databaseReference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id = ""+snapshot.child("id").getValue();
                plant = ""+snapshot.child("plant").getValue();
                binding.etNameP.setText(plant);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void loadplant() {
        theplantArrayList = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("plant");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                theplantArrayList.clear();

                for (DataSnapshot ds:snapshot.getChildren()){
                    if (snapshot!=null){
                      id = ""+ds.child("id").getValue();
                     plant= ""+ds.child("plant").getValue();
                        Log.d("onDataChange: ",snapshot.toString());
                        Log.d("onDataChange: ",snapshot.getValue().toString());
                        Log.d("onDataChange: ",snapshot.getKey().toString());
                        theplantArrayList.add(id);


                    }
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                error.toException().printStackTrace();

            }
        });

    }
}