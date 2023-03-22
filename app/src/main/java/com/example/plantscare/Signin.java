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

import com.example.plantscare.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signin extends AppCompatActivity {
    ActivitySigninBinding binding;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("please wait ...");
        progressDialog.setCanceledOnTouchOutside(false);

        binding.tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),Signup.class);
                startActivity(intent);
//                finish();
            }
        });

        binding.btnSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valideData();
            }
        });

    }
    private String email="",password="";
    private void valideData() {
        email=binding.emailEt.getText().toString();
        password=binding.passwordEt.getText().toString();


        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "enter your email", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "enter password", Toast.LENGTH_SHORT).show();
        }else {
            UserLogin();
        }
    }

    private void UserLogin() {
        progressDialog.setMessage("login ");
        progressDialog.dismiss();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                CheakUser();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Signin.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void CheakUser() {
        progressDialog.setMessage("cheaking user..");
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("users");
        databaseReference.child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String usertype = "" + dataSnapshot.child("usertype").getValue();


                Intent intent = new Intent(Signin.this, Activity_sections.class);
                startActivity(intent);
//                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        binding.tvFogotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),ForgetPassword.class);
                startActivity(intent);

            }
        });
    }
}