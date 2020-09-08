package com.example.cst438_project1;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private String instructor;

    private String title;

    private String description;

    private String sDate;

    private String eDate;

    public Course(String instructor, String title, String description, String sDate, String eDate) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.sDate = sDate;
        this.eDate = eDate;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }

    public String getInstructor() {
        return instructor;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getSDate() {
        return sDate;
    }

    public String getEDate() {
        return eDate;
    }
}
