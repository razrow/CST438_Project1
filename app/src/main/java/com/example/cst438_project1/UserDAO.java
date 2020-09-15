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

    @Query("delete from user_table")
    void deleteAllUsers();

    @Query("select * from user_table")
    LiveData<List<User>> getAllUsers();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM user_table WHERE username = :username")
    User getUsername(String username);

    @Query("UPDATE user_table SET username=:updatedUsername WHERE username=:username")
    void updateUsername(String username, String updatedUsername);

    @Query("UPDATE user_table SET fName=:updatedFirstName WHERE username=:username")
    void updateFirstName(String username, String updatedFirstName);

    @Query("UPDATE user_table SET lName=:updatedLastName WHERE username=:username")
    void updateLastName(String username, String updatedLastName);

    @Query("UPDATE user_table SET password=:updatedPassword WHERE username=:username")
    void updatePassword(String username, String updatedPassword);


}
