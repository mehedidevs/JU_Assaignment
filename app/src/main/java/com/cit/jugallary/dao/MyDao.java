package com.cit.jugallary.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cit.jugallary.entities.Gallery;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    void insert(Gallery gallery);

    @Update
    void update(Gallery gallery);

    @Delete
    void delete(Gallery gallery);

    @Query("select * from ju_gallery")
    List<Gallery> getAllImage();
}
