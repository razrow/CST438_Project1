package com.example.cst438_project1;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "course_table")
public class Course {

    @PrimaryKey(autoGenerate = true)
    private int courseID;

    private String instructor;

    private String title;

    private String description;

    private String sDate;

    private String eDate;

    private String username;

    /**
     * Full constructor
     * @param instructor - String - the instructor's name
     * @param title -  String - the title of the course
     * @param description - String - a description of the course
     * @param sDate - String - the start date of the course
     * @param eDate - String - the end date of the course
     */
    public Course(String instructor, String title, String description, String sDate, String eDate, String username) {
        this.instructor = instructor;
        this.title = title;
        this.description = description;
        this.sDate = sDate;
        this.eDate = eDate;
        this.username = username;
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

    public String getUsername(){return username; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return instructor.equals(course.instructor) &&
                title.equals(course.title) &&
                description.equals(course.description) &&
                sDate.equals(course.sDate) &&
                eDate.equals(course.eDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, sDate, eDate);
    }
}
