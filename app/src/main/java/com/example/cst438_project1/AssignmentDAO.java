package com.example.cst438_project1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface AssignmentDAO {

    @Query("delete from Assignment")
    void deleteAllAssignment();

    @Query("select * from Assignment")
    LiveData<List<User>> getAllAssignments();

    @Insert
    void insert(Assignment assignment);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

}
