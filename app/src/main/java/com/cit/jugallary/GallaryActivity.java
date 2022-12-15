package com.cit.jugallary;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cit.jugallary.adapter.GalleryAdapter;
import com.cit.jugallary.databinding.ActivityMainBinding;
import com.cit.jugallary.db.MyDB;
import com.cit.jugallary.entities.Gallery;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.ArrayList;
import java.util.List;

public class GallaryActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    GalleryAdapter adapter;
    List<Gallery> galleryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //binding.toolbar.toolbarTxt
        galleryList = new ArrayList<>();
        binding.toolbar.getRoot().setTitle("");
        setSupportActionBar(binding.toolbar.getRoot());

        binding.toolbar.backBtn.setOnClickListener(v -> {

            finish();
        });



        requestPermission();

        galleryList = MyDB.getMyDB(GallaryActivity.this).myDao().getAllImage();

        adapter = new GalleryAdapter(GallaryActivity.this,galleryList);
        binding.recycler.setAdapter(adapter);
    }

    private void requestPermission() {

        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
                    @Override public void onPermissionsChecked(MultiplePermissionsReport report) {
                        }
                    @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        }
                }).check();
    }




}