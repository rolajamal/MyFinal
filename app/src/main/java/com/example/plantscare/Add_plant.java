package com.example.plantscare;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.plantscare.databinding.ActivityAddPlantBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Add_plant extends AppCompatActivity {
    ActivityAddPlantBinding binding;
    FirebaseAuth firebaseAuth;
    Uri ImgUri = null;
    public static final int PICK_IMAGE_REQUEST=1;
    DatabaseReference dbrf;
    ValueEventListener listener;
    ArrayList<String>list;
    ArrayAdapter<String>adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPlantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();


        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valideData();
                insertData();


            }
        });
        list=new ArrayList<String>();
        adapter=new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,list);
        binding.SpCountry.setAdapter(adapter);

        dbrf=FirebaseDatabase.getInstance().getReference("SpinnerData");
        binding.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowImgeAttachMenu();
            }
        });

        binding.btnDiseases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Add_plant.this, Add_diseeases.class);
                startActivity(intent);
            }
        });


    }

    private void insertData() {
        String data=binding.etSp.getText().toString();
        dbrf.push().setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                binding.etSp.setText("");
                list.clear();
                fetchData();
                adapter.notifyDataSetChanged();
                Toast.makeText(Add_plant.this, "spinner added sucssful....", Toast.LENGTH_SHORT).show();



            }
        });
        fetchData();
    }

    private void fetchData() {
        listener=dbrf.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mydata : snapshot.getChildren())
                    list.add(mydata.getValue().toString());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    String Plant_name = "";
    String Details = "";
    String Diseases = "";
    String Category = "";
    String image = "";


    private void valideData() {

        Plant_name = binding.etNameP.getText().toString();
        Details = binding.etDetails.getText().toString();



        if (TextUtils.isEmpty(Plant_name)) {
            Toast.makeText(this, "please enter plant", Toast.LENGTH_SHORT).show();

        } else if (TextUtils.isEmpty(Details)) {
            Toast.makeText(this, "please enter details", Toast.LENGTH_SHORT).show();
//        } else if (ImgUri == null) {
//            updateProfile("");
//        } else {
//            addPlantFirebase();
//            uploadImge();

        }

    }

    private void addPlantFirebase() {


        long timestamp = System.currentTimeMillis();


        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", "" + timestamp);
        hashMap.put("plant", Plant_name);
        hashMap.put("details", Details);
        hashMap.put("diseases", Diseases);
        hashMap.put("category", Category);
        hashMap.put("timestamp", timestamp);
        hashMap.put("uid", firebaseAuth.getUid());


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("plant");
        databaseReference.child("" + timestamp).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Add_plant.this, "plant added sucssful....", Toast.LENGTH_SHORT).show();


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Add_plant.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }





        private void ShowImgeAttachMenu(){
            PopupMenu popupMenu = new PopupMenu(this, binding.img);
            popupMenu.getMenu().add(Menu.NONE, 0, 0, "camera");
            popupMenu.getMenu().add(Menu.NONE, 1, 1, "Gallery");
            popupMenu.show();

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {

                    int witch = menuItem.getItemId();
                    if (witch == 0) {
                        PageImgeCammera();

                    } else if (witch == 2) {
                        PageImgeGallery();
                    }
                    return false;
                }
            });


        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data !=null && data.getData() !=null){
            ImgUri=data.getData();


        }
    }

    private void PageImgeGallery() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);






    }

    ActivityResultLauncher<Intent> CammeraActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();

                                binding.img.setImageURI(ImgUri);
                            } else {
                                Toast.makeText(Add_plant.this, "canselled...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );





    private void PageImgeCammera(){
            ContentValues values=new ContentValues();
            values.put(MediaStore.Images.Media.TITLE,"new Pick");
            values.put(MediaStore.Images.Media.DESCRIPTION,"Sample description");
            ImgUri=getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);

            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT,ImgUri);
            CammeraActivityResultLauncher.launch(intent);


        }




//    ActivityResultLauncher<Intent>GalleryActivityResultLauncher=
//            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//                    new ActivityResultCallback<ActivityResult>() {
//                        @Override
//                        public void onActivityResult(ActivityResult result) {
//                            if(result.getResultCode()== Activity.RESULT_OK){
//                                Intent data=result.getData();
//                                ImgUri=data.getData();
//                                binding.img.setImageURI(ImgUri);
//                            }else {
//                                Toast.makeText(Add_plant.this, "canselled...", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//            );


//        private void PageImgeGallery() {
//            Intent intent=new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            GalleryActivityResultLauncher.launch(intent);
//        }

//
//        private void uploadImge () {
//
//            String filePathAndName = "PlantsImages/" + firebaseAuth.getUid();
//            StorageReference reference = FirebaseStorage.getInstance().getReference(filePathAndName);
//
//
//            reference.putFile(ImgUri)
//                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                            Task<Uri> task = taskSnapshot.getStorage().getDownloadUrl();
//
//                            while (!task.isSuccessful()) {
//                                String uploadImageUri = "" + task.getResult();
//                                updateProfile(uploadImageUri);
//                            }
//
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//
//                            Toast.makeText(Add_plant.this, "failed to upload image..", Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//        }
//
//        private void updateProfile (String imageUri){
//
//
//            HashMap<String, Object> hashMap = new HashMap<>();
//            hashMap.put("ImgUri", "" + ImgUri);
//
//            if (ImgUri == null) {
//                hashMap.put("PlantsImage", "" + ImgUri);
//
//                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("plant");
//
//                ref.child(firebaseAuth.getUid())
//                        .updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
//                            @Override
//                            public void onSuccess(Void unused) {
//                                Toast.makeText(Add_plant.this, "profile update..", Toast.LENGTH_SHORT).show();
//
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Add_plant.this, "failed to update image.." + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        }


    }
