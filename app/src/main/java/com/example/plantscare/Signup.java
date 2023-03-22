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
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valideData();

            }


        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Signin.class);
                startActivity(intent);
            }
        });
    }
    private String name="" ,email = "",password="",Cpassword="";

    private void valideData() {
        name=binding.usernameEt.getText().toString();
        email=binding.emailEt.getText().toString();
        password=binding.passwordEt.getText().toString();
        Cpassword=binding.confirmpassEt.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "enter your name", Toast.LENGTH_SHORT).show();
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(Cpassword)){
            Toast.makeText(this, "enter cpassword", Toast.LENGTH_SHORT).show();
        }else if(!password.equals(Cpassword)){
            Toast.makeText(this, "password does not matches", Toast.LENGTH_SHORT).show();
        }else {
            createUserAccount();
        }

    }

    private void createUserAccount() {
        progressDialog.setMessage("creating Account..");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                updateUserinfo();

            }


        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Signup.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void updateUserinfo() {
        progressDialog.setTitle("Saving User info...");
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
                progressDialog.dismiss();
                Toast.makeText(Signup.this, "account created...", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Signup.this,Activity_sections.class);

                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Signup.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}