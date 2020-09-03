package com.example.simpletodo;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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
