package com.example.cst438_project1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface EnrollmentDAO {

    @Query("delete from Enrollment")
    void deleteAllEnrollments();

    @Query("select * from Enrollment")
    LiveData<List<Enrollment>> getAllEnrollments();

    @Insert
    void insert(Enrollment enrollment);

    @Update
    void update(Enrollment enrollment);

    @Delete
    void delete(Enrollment enrollment);

}
