package com.cit.jugallary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.cit.jugallary.databinding.ActivityDetailsBinding;

public class DetailsActivity extends AppCompatActivity {

    ActivityDetailsBinding binding;
    Intent intent;
    Uri imageUri;
    String imageStr,date,title,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbar.getRoot().setTitle("");
        setSupportActionBar(binding.toolbar.getRoot());

        binding.toolbar.backBtn.setOnClickListener(v -> {

            finish();
        });



        intent = getIntent();
        if (intent.hasExtra("uri")){
            imageStr = intent.getStringExtra("uri");
            imageUri = Uri.parse(imageStr);
            title = intent.getStringExtra("title");
            desc = intent.getStringExtra("desc");
            date = intent.getStringExtra("date");
        }

        binding.img.setImageURI(imageUri);
        binding.title.setText(title);
        binding.desc.setText(desc);
        binding.date.setText(date);
    }
}