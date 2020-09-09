package com.example.cst438_project1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("delete from User")
    void deleteAllUsers();

    @Query("select * from User")
    LiveData<List<User>> getAllUsers();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM User WHERE username = :username")
    User getUsername(String username);

}
