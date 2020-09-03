package com.example.cst438_project1;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Enrollment {

    @PrimaryKey(autoGenerate = true)
    private int studentID;

    private int courseID;

    private String enrollDate;

    public Enrollment(int courseID, String enrollDate) {
        this.courseID = courseID;
        this.enrollDate = enrollDate;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getEnrollDate() {
        return enrollDate;
    }
}
