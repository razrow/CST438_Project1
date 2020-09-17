package com.example.cst438_project1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import com.example.cst438_project1.Course;
import com.example.cst438_project1.Assignment;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int userID;

    private String username;

    private String password;

    private String fName;

    private String lName;

//    private ArrayList<Course> courses = new ArrayList<Course>();
//
//    private ArrayList<Assignment> assignments = new ArrayList<Assignment>();
//
//    private HashMap<Course, ArrayList<Assignment>> courseAssignments = new HashMap<Course, ArrayList<Assignment>>();

    /**
     * Full constructor
     * @param username - String - represents the user's username
     * @param password - String - represents the user's password
     * @param fName - String - User's first name
     * @param lName - String - User's last name
     */
    public User(String username, String password, String fName, String lName) {
        this.username = username;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

//    public ArrayList<Course> getCourses() {
//        return courses;
//    }
//
//    public void setCourses(ArrayList<Course> courses) {
//        this.courses = courses;
//    }
//    public void setCourses() {
//        this.courses = new ArrayList<Course>();
//    }
//
//    public ArrayList<Assignment> getAssignments() {
//        return assignments;
//    }
//
//    public void setAssignments(ArrayList<Assignment> assignments) {
//        this.assignments = assignments;
//    }
//
//    public void setAssignments() {
//        this.assignments = new ArrayList<Assignment>();
//    }
//
//    public HashMap<Course, ArrayList<Assignment>> getCourseAssignments() {
//        return courseAssignments;
//    }
//
//    public void setCourseAssignments(HashMap<Course, ArrayList<Assignment>> courseAssignments) {
//        this.courseAssignments = courseAssignments;
//    }
//
//    public void setCourseAssignments() {
//        this.courseAssignments = new HashMap<>();
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}