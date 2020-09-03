package com.example.cst438_project1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface GradeDAO {

    @Query("delete from Grade")
    void deleteAllGrades();

    @Query("select * from Grade")
    LiveData<List<Grade>> getAllGrades();

    @Insert
    void insert(Grade grade);

    @Update
    void update(Grade grade);

    @Delete
    void delete(Grade grade);

}
