package com.example.plantscare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.plantscare.database.Categories;
import com.example.plantscare.database.Theplant;
import com.example.plantscare.databinding.ActivityAddAcategoryBinding;
import com.example.plantscare.databinding.ActivityViewTheCategoriesBinding;
import com.example.plantscare.databinding.ActivityViewplantBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class viewplant extends AppCompatActivity {
    ActivityViewplantBinding binding;
    FirebaseAuth firebaseAuth;
    ArrayList<Theplant> theplantArrayList;
    Adapterviewplant adapterplant;
    Theplant modelplant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityViewplantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();
            loadPlant();


            binding.searshEt.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        private void loadPlant() {
          theplantArrayList=new ArrayList<>();
            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("plant");

            databaseReference.addValueEventListener(new ValueEventListener() {
                @SuppressLint("SuspiciousIndentation")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                  theplantArrayList.clear();

                    for (DataSnapshot ds:snapshot.getChildren()){
                    modelplant=ds.getValue(Theplant.class);

                       theplantArrayList.add(modelplant);
                    }

                 adapterplant=new Adapterviewplant(theplantArrayList);
                    binding.plantList.setAdapter(adapterplant);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }




    }




//        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                valideData();
//
//            }
//        });
//
//        binding.backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
//
//
//    }
//    String Category_name="";
//    private void valideData() {
//
//        Category_name=binding.NameCgtAddEt.getText().toString();
//
//        if(TextUtils.isEmpty(Category_name)){
//            Toast.makeText(this, "please enter category", Toast.LENGTH_SHORT).show();
//        }else {
//            addCategoryFirebase();
//        }
//
//    }
//
//    private void addCategoryFirebase() {
//
//
//        long timestamp= System.currentTimeMillis();
//
//
//
//        HashMap<String , Object> hashMap=new HashMap<>();
//        hashMap.put("id",""+timestamp);
//        hashMap.put("category",Category_name);
//        hashMap.put("timestamp",timestamp);
//        hashMap.put("uid",firebaseAuth.getUid());
//
//
//        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("category");
//        databaseReference.child(""+timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//                Toast.makeText(AddACategory.this, "category added sucssful....", Toast.LENGTH_SHORT).show();
//
//
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//
//                Toast.makeText(AddACategory.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//    }
//}