package com.example.cst438_project1;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Grade.class, Enrollment.class, Course.class, Category.class, Assignment.class}, version = 1 )
public abstract class UserDB extends RoomDatabase {

    private static UserDB instance;

    public abstract UserDAO userDao();

    public static synchronized UserDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "user_db").
                    fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
