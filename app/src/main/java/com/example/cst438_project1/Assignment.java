package com.example.cst438_project1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "assignment_table")
public class Assignment {

    @PrimaryKey(autoGenerate = true)
    private int assignmentID;

    private String dets;

    private int maxScore;

    private int earnedScore;

    //private String assignedDate;

    //private String dueDate;

    //private int categoryID;

    //private int courseID;

    /*public Assignment(String dets, int maxScore, int earnedScore, String assignedDate, String dueDate, int categoryID, int courseID) {
        this.dets = dets;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;
        this.assignedDate = assignedDate;
        this.dueDate = dueDate;
        this.categoryID = categoryID;
        this.courseID = courseID;
    }*/

    // I have a feeling we should use this constructor for now since we dont need
    // all the extra stuff

    /**
     * Full constructor
     * @param dets - String - representing the details of the assignment
     * @param maxScore - int - the max score you can score on the assignment
     * @param earnedScore - int - the score earned on the assignment
     */
    public Assignment(String dets, int maxScore, int earnedScore) {
        this.dets = dets;
        this.maxScore = maxScore;
        this.earnedScore = earnedScore;

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

    /*public String getAssignedDate() {
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
    }*/

    public void setDets(String dets) {
        this.dets = dets;
    }

    public void setMaxScore(int maxScore) {
        this.maxScore = maxScore;
    }

    public void setEarnedScore(int earnedScore) {
        this.earnedScore = earnedScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return maxScore == that.maxScore &&
                earnedScore == that.earnedScore &&
                dets.equals(that.dets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dets, maxScore, earnedScore);
    }
}
