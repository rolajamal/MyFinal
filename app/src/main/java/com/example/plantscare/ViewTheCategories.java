package com.example.plantscare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.example.plantscare.database.Categories;
import com.example.plantscare.databinding.ActivityViewTheCategoriesBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewTheCategories extends AppCompatActivity {
    ActivityViewTheCategoriesBinding binding;
    FirebaseAuth firebaseAuth;
    ArrayList<Categories>categoriesArrayList;
    AdapterCategory adapterCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityViewTheCategoriesBinding.inflate(getLayoutInflater()) ;
        setContentView(binding.getRoot());
        firebaseAuth= FirebaseAuth.getInstance();
        loadCategory();


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

    private void loadCategory() {
        categoriesArrayList=new ArrayList<>();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("category");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoriesArrayList.clear();

                for (DataSnapshot ds:snapshot.getChildren()){
                    Categories modelCategory=ds.getValue(Categories.class);
                    categoriesArrayList.add(modelCategory);
                }
                AdapterCategory adapterCategory=new AdapterCategory(categoriesArrayList);
                binding.categoryList.setAdapter(adapterCategory);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}