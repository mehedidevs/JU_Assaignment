package com.cit.jugallary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.cit.jugallary.adapter.GalleryAdapter;
import com.cit.jugallary.databinding.ActivityMainBinding;
import com.cit.jugallary.db.MyDB;
import com.cit.jugallary.entities.Gallery;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

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
        binding.toolbar.backBtn.setVisibility(View.GONE);


        requestPermission();

        galleryList = MyDB.getMyDB(MainActivity.this).myDao().getAllImage();

        adapter = new GalleryAdapter(MainActivity.this, galleryList);
        binding.recycler.setAdapter(adapter);
    }

    private void requestPermission() {
        Dexter.withContext(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                ).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    }
                }).check();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.gallery:
                startActivity(new Intent(MainActivity.this, GallaryActivity.class));
                break;

            case R.id.add:
                startActivity(new Intent(MainActivity.this, AddImageActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}