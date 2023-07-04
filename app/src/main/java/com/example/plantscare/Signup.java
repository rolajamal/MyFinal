package com.example.plantscare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.plantscare.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Signup extends AppCompatActivity {
    ActivitySignupBinding binding;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth=FirebaseAuth.getInstance();

        binding.btnREGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valideData();
            }
        });

        binding.btnREGISTER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Signin.class);
                startActivity(intent);
            }
        });
//        binding.logTv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getBaseContext(),Signin.class);
//                startActivity(intent);
//
//            }
//        });



    }
    private String name="" ,email = "",password="",cn_pass="";

    private void valideData() {
        name=binding.EtUsername.getText().toString();
        email=binding.EtEmail.getText().toString();
        password=binding.EtPass.getText().toString();
        cn_pass=binding.EtCnPass.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "enter your name", Toast.LENGTH_SHORT).show();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(cn_pass)){
            Toast.makeText(this, "enter cn-password", Toast.LENGTH_SHORT).show();
        }else {
            createUserAccount(name,email,password,cn_pass);
        }

    }

    private void createUserAccount(String name,String email,String password,String phone ) {
;

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                updateUserinfo();

            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateUserinfo() {
        long timestamp= System.currentTimeMillis();

        String uid=firebaseAuth.getUid();

        HashMap<String , Object> hashMap=new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("email",email);
        hashMap.put("name",name);
        hashMap.put("profileImge","");
        hashMap.put("usertype","user");
        hashMap.put("timestamp",timestamp);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(uid).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Signup.this, "account created...", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Signup.this,Signin.class);

                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Signup.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}