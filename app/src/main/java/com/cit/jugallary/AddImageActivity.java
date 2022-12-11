package com.cit.jugallary;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.cit.jugallary.databinding.ActivityAddImageBinding;
import com.cit.jugallary.db.MyDB;
import com.cit.jugallary.entities.Gallery;
import com.github.dhaval2404.imagepicker.ImagePicker;

public class AddImageActivity extends AppCompatActivity {

    ActivityAddImageBinding binding;

    Uri imageUri;
    long currentDate = System.currentTimeMillis();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.floatingActionButton.setOnClickListener(v -> {
            pickImage();
        });

        binding.saveBtn.setOnClickListener(v -> {
            String title = binding.title.getText().toString();
            String desc = binding.desc.getText().toString();
            String imageStr = String.valueOf(imageUri);
            if (title.equals("")){
                inputValidationAlert("Title can't be empty!");
            }else if (desc.equals("")){
                inputValidationAlert("Description can't be empty!");
            }else if (imageStr.equals("")){
                inputValidationAlert("Please select an image!");
            }else {
                Gallery gallery = new Gallery(imageStr,title,desc,currentDate);
                MyDB.getMyDB(AddImageActivity.this).myDao().insert(gallery);
                startActivity(new Intent(AddImageActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    private void inputValidationAlert(String s) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddImageActivity.this);
        builder.setMessage(s)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create().show();
    }

    private void pickImage() {

        ImagePicker.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start(101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            binding.img.setImageURI(imageUri);
        }
    }
}