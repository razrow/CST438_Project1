package com.example.simpletodo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CourseDAO {

    @Query("delete from Course")
    void deleteAllCourses();

    @Query("select * from Course")
    LiveData<List<Course>> getAllCourses();

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

}