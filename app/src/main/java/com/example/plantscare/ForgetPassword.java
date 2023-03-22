package com.example.plantscare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.plantscare.databinding.ActivityForgetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {
    ActivityForgetPasswordBinding binding;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth=FirebaseAuth.getInstance();

        binding.btnForgrtpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }
        });

    }
    private String email="";
    private void validateData() {

        email=binding.emailfpassEt.getText().toString();
//        password=binding.passwordEtfpass.getText().toString();

        if(email.isEmpty()) {
            binding.emailfpassEt.setError("Required");

        }else {
            forgetPass();
        }

    }

    private void forgetPass() {
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPassword.this, "Cheak your Email", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getBaseContext(),Signin.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(ForgetPassword.this, "Error:"+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}