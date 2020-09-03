package com.example.simpletodo;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class, Grade.class, Enrollment.class, Course.class, Category.class, Assignment.class}, version = 1)
public abstract class UserDB extends RoomDatabase{

    private static UserDB instance;

    public abstract UserDAO userDao();
    public abstract GradeDAO gradeDao();
    public abstract EnrollmentDAO enrollDao();
    public abstract CourseDAO courseDao();
    public abstract CategoryDAO categoryDao();
    public abstract AssignmentDAO assignmentDao();

    public static synchronized UserDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, "user_db")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
