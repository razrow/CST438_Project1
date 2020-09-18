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

    @Query("delete from assignment_table")
    void deleteAllAssignment();

    @Query("select * from assignment_table")
    List<Assignment> getAllAssignments();

    @Insert
    void insert(Assignment assignment);

    @Update
    void update(Assignment assignment);

    @Delete
    void delete(Assignment assignment);

    @Query("SELECT * FROM assignment_table WHERE assignmentID=:id")
    Assignment getAssignmentById(int id);

    @Query("SELECT * FROM assignment_table wHERE dets=:details")
    Assignment getAssignmentByDetails(String details);

    @Query("UPDATE assignment_table SET dets=:updatedDetails WHERE assignmentID=:assignmentId")
    void updateDetails(int assignmentId, String updatedDetails);

    @Query("UPDATE assignment_table SET maxScore=:updatedMaxScore WHERE assignmentID=:assignmentId")
    void updateMaxScore(int assignmentId, int updatedMaxScore);

    @Query("UPDATE assignment_table SET earnedScore=:updatedEarnedScore WHERE assignmentID=:assignmentId")
    void updateEarnedScore(int assignmentId, int updatedEarnedScore);

    @Query("UPDATE assignment_table SET dets=:updatedDetails WHERE dets=:details")
    void updateDetails(String details, String updatedDetails);

    @Query("UPDATE assignment_table SET maxScore=:updatedMaxScore WHERE dets=:details")
    void updateMaxScore(String details, int updatedMaxScore);

    @Query("UPDATE assignment_table SET earnedScore=:updatedEarnedScore WHERE dets=:details")
    void updateEarnedScore(String details, int updatedEarnedScore);
}
