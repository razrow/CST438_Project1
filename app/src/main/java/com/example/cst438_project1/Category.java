package com.example.cst438_project1;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey(autoGenerate = true)
    private int categoryID;

    private int gradeID;

    private String title;

    private String assignedDate;

    private int weight;

    public Category(int gradeID, String title, String assignedDate, int weight) {
        this.gradeID = gradeID;
        this.title = title;
        this.assignedDate = assignedDate;
        this.weight = weight;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getGradeID() {
        return gradeID;
    }

    public String getTitle() {
        return title;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public int getWeight() {
        return weight;
    }
}
