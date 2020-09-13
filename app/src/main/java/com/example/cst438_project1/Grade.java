package com.example.cst438_project1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "grade_table")
public class Grade {

    @PrimaryKey(autoGenerate = true)
    private int gradeID;

    private int score;

    private int assignmentID;

    private int studentID;

    private int courseID;

    private String dateEarned;

    /**
     * Full constructor
     * @param score - int - the score that was scored on the assignment
     * @param assignmentID - int - the assignmentId that corresponds with the database table
     * @param studentID - int - the studentId that corresponds with the database table
     * @param courseID - int - the courseId that corresponds with the database table
     * @param dateEarned - String - the date the grade was earned
     */
    public Grade(int score, int assignmentID, int studentID, int courseID, String dateEarned) {
        this.score = score;
        this.assignmentID = assignmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.dateEarned = dateEarned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return score == grade.score &&
                assignmentID == grade.assignmentID &&
                studentID == grade.studentID &&
                courseID == grade.courseID &&
                dateEarned.equals(grade.dateEarned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(score, assignmentID, studentID, courseID, dateEarned);
    }

    public void setGradeID(int gradeID) {
        this.gradeID = gradeID;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public void setDateEarned(String dateEarned) {
        this.dateEarned = dateEarned;
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
