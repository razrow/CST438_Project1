package com.example.cst438_project1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("delete from Category")
    void deleteAllCategory();

    @Query("select * from Category")
    LiveData<List<Category>> getAllCategory();

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

}
