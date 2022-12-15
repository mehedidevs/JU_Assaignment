package com.cit.jugallary.entities;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ju_gallery")
public class Gallery {
    @PrimaryKey(autoGenerate = true)
    int id;
    String imageUri;
    String imageName,imageDetails;
    long dateMillis;



    public Gallery(String imageUri, String imageName, String imageDetails, long dateMillis) {
        this.imageUri = imageUri;
        this.imageName = imageName;
        this.imageDetails = imageDetails;
        this.dateMillis = dateMillis;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(String imageDetails) {
        this.imageDetails = imageDetails;
    }

    public long getDateMillis() {
        return dateMillis;
    }

    public void setDateMillis(long dateMillis) {
        this.dateMillis = dateMillis;
    }
}
