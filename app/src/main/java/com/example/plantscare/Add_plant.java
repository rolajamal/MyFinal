package com.example.plantscare;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;

import android.view.View;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.plantscare.databinding.ActivityAddPlantBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import android.Manifest;


    public class Add_plant extends AppCompatActivity {
        private static final int REQUEST_IMAGE_CAPTURE = 1;
        private static final int REQUEST_IMAGE_PICK = 2;
        private static final int PERMISSION_REQUEST_CODE = 3;

        private ActivityAddPlantBinding binding;
        private FirebaseAuth firebaseAuth;
        private DatabaseReference databaseReference;
        private FirebaseStorage storage;
        private StorageReference storageRef;

        private String plantName = "";
        private String details = "";
        private String diseases = "";
        private String category = "";
        private Uri imageUri;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityAddPlantBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            firebaseAuth = FirebaseAuth.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference("plant");
            storage = FirebaseStorage.getInstance();
            storageRef = storage.getReference();

            binding.btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    validateData();
                    addPlantToFirebase();
                }
            });

            binding.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showImageOptions();
                }
            });

            binding.btnDiseases.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getBaseContext(), Add_diseeases.class);
                    startActivity(intent);
                }
            });
        }

        private void showImageOptions() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Choose Image Source");
            builder.setItems(new CharSequence[]{"Capture Photo", "Select from Gallery"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            checkCameraPermission();
                            break;
                        case 1:
                            checkStoragePermission();
                            break;
                    }
                }
            });
            builder.show();
        }

        private void checkCameraPermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CODE);
            } else {
                captureImage();
            }
        }

        private void checkStoragePermission() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            } else {
                pickImageFromGallery();
            }
        }

        private void captureImage() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

        private void pickImageFromGallery() {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "Select Image"), REQUEST_IMAGE_PICK);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_OK) {
                if (requestCode == REQUEST_IMAGE_CAPTURE) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    binding.img.setImageBitmap(imageBitmap);
                    imageUri = getImageUri(imageBitmap);
                } else if (requestCode == REQUEST_IMAGE_PICK) {
                    imageUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                        binding.img.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        private Uri getImageUri(Bitmap bitmap) {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "PlantImage", null);
            return Uri.parse(path);
        }

        private void validateData() {
            plantName = binding.etNameP.getText().toString().trim();
            details = binding.etDetails.getText().toString().trim();
            category = binding.etCat.getText().toString().trim();

            if (TextUtils.isEmpty(plantName)) {
                Toast.makeText(this, "Enter plant name", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(details)) {
                Toast.makeText(this, "Enter plant details", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(category)) {
                Toast.makeText(this, "Select plant category", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        private void addPlantToFirebase() {
            if (imageUri == null) {
                Toast.makeText(this, "Select plant image", Toast.LENGTH_SHORT).show();
                return;
            }

            String plantId = databaseReference.push().getKey();
            StorageReference imageReference = storageRef.child("plant_images").child(plantId + ".jpg");

            imageReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    HashMap<String, Object> hashMap = new HashMap<>();
                                    hashMap.put("plantId", plantId);
                                    hashMap.put("plantName", plantName);
                                    hashMap.put("details", details);
                                    hashMap.put("category", category);
                                    hashMap.put("imageUri", uri.toString());
                                    hashMap.put("userId", firebaseAuth.getUid());

                                    databaseReference.child(plantId).setValue(hashMap)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getBaseContext(), "Plant added successfully", Toast.LENGTH_SHORT).show();
                                                    clearFields();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getBaseContext(), "Failed to add plant: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getBaseContext(), "Failed to upload image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        private void clearFields() {
            binding.etNameP.setText("");
            binding.etDetails.setText("");
            binding.SpCat.setSelection(0);
            binding.img.setImageResource(R.drawable.carrot);
            imageUri = null;
        }

    }





