package com.example.cst438_project1;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface GradeDAO {

    @Query("delete from grade_table")
    void deleteAllGrades();

    @Query("select * from grade_table")
    LiveData<List<Grade>> getAllGrades();

    @Insert
    void insert(Grade grade);

    @Update
    void update(Grade grade);

    @Delete
    void delete(Grade grade);

}
