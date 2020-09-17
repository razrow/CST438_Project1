package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class CourseDisplay extends AppCompatActivity {

    public static final String KEY_COURSE_TEXT = "course_text";
    public static final String KEY_COURSE_POSITION = "course_position";
    public static final int EDIT_TEXT_CODE = 20;

    List<String> courses;
    Button btnAdd;
    EditText etCourse;
    RecyclerView rvCourses;
    CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_display);

        btnAdd = findViewById(R.id.btnAdd);
        etCourse = findViewById(R.id.etCourse);
        rvCourses = findViewById(R.id.rvCourses);

        etCourse.setText("Add Course Here");

        courses = new ArrayList<>();
        courses.add("Example Course");

        new CourseAdapter(courses);
        rvCourses.setAdapter(courseAdapter);
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
    }
}