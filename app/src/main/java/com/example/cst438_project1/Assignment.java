package com.example.cst438_project1;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Assignment {

    @PrimaryKey(autoGenerate = true)
    private int assignmentID;

    private String dets;

    private int maxScore;

    private int earnedScore;

    private String assignedDate;

    private String dueDate;

    private int categoryID;

    private int courseID;

    public Assignment(String dets, int maxScore, int earnedScore, String assignedDate, String dueDate, int categoryID, int courseID) {
        this.dets = dets;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.categoryID = categoryID;
        this.courseID = courseID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public String getDets() {
        return dets;
    }

    public int getMaxScore() {
        return maxScore;
    }

    public int getEarnedScore() {
        return earnedScore;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getCourseID() {
        return courseID;
    }
}
