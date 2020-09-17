package com.example.cst438_project1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface CourseDAO {

    @Query("delete from course_table")
    void deleteAllCourses();

    @Query("select * from course_table")
    List<Course> getAllCourses();

    @Insert
    void insert(Course course);

    @Update
    void update(Course course);

    @Delete
    void delete(Course course);

    @Query("SELECT * FROM course_table WHERE courseID=:id")
    Course getCourseById(int id);

    @Query("SELECT * FROM course_table wHERE dets=:details")
    Course getCourseByDetails(String details);

//    @Query("SELECT * FROM course_table WHERE username =:username")
//    ArrayList<Course>() getCourseByUsername(String username);

    @Query("SELECT * FROM course_table WHERE title =:courseTitle")
    Course getCourse(String courseTitle);

    @Query("UPDATE course_table SET instructor=:updatedInstructor WHERE instructor=:instructor")
    void updateInstructor(String instructor, String updatedInstructor);

    @Query("UPDATE course_table SET title =:updatedTitle WHERE instructor=:instructor")
    void updateTitle(String instructor, String updatedTitle);

    @Query("UPDATE course_table SET description=:updatedDescription WHERE instructor=:instructor")
    void updateDescription(String instructor, String updatedDescription);

    @Query("UPDATE course_table SET instructor=:updatedInstructor WHERE courseID=:courseId")
    void updateInstructor(int courseId, String updatedInstructor);

    @Query("UPDATE course_table SET title =:updatedTitle WHERE courseID=:courseId")
    void updateTitle(int courseId, String updatedTitle);

    @Query("UPDATE course_table SET description=:updatedDescription WHERE courseID=:courseId")
    void updateDescription(int courseId, String updatedDescription);

    @Query("UPDATE course_table SET sDate=:updateSDate WHERE instructor=:instructor")
    void updateSDate(String instructor, String updateSDate);

    @Query("UPDATE course_table SET eDate=:updateEDate WHERE instructor=:instructor")
    void updateEDate(String instructor, String updateEDate);

    @Query("UPDATE course_table SET dets=:updatedDetails WHERE dets=:details")
    void updateDetails(int details, String updatedDetails);

}
