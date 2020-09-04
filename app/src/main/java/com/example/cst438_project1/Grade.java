package com.example.cst438_project1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Grade {

    @PrimaryKey(autoGenerate = true)
    private int gradeID;

    private int score;

    private int assignmentID;

    private int studentID;

    private int courseID;

    private String dateEarned;

    public Grade(int score, int assignmentID, int studentID, int courseID, String dateEarned) {
        this.score = score;
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.dateEarned = dateEarned;
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public int getGradeID() {
        return gradeID;
    }

    public int getScore() {
        return score;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getDateEarned() {
        return dateEarned;
    }
}
