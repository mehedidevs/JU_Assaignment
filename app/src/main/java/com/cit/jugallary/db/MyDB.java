package com.cit.jugallary.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cit.jugallary.dao.MyDao;
import com.cit.jugallary.entities.Gallery;

@Database(entities = {Gallery.class}, version = 1)
public abstract class MyDB extends RoomDatabase {
    public abstract MyDao myDao();
     static MyDB myDB = null;
    public static MyDB getMyDB(Context context){
        if (myDB == null){
            myDB = Room.databaseBuilder(
                    context,
                    MyDB.class,
                    "ju_gallery.db").allowMainThreadQueries().build();
        }
        return myDB;
    }
}
