package com.example.cst438_project1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
