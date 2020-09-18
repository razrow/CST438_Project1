package com.example.cst438_project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CourseDisplay extends AppCompatActivity {

    public static final String KEY_COURSE_TEXT = "course_text";
    public static final String KEY_COURSE_POSITION = "course_position";
    public static final int EDIT_TEXT_CODE = 20;

    List<String> courses = new ArrayList<>();
    Button btnAdd;
    EditText etCourse;
    RecyclerView rvCourses;
    CourseAdapter courseAdapter;
    String mUsername = "placeholder";
    TextView displayUsername;
    Button editUser;
    UserDAO mUserDAO;
    CourseDAO mCourseDAO;
    User mUser;

    public static final String test = "ALL_GOOD";

    String method;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_display);

        btnAdd = findViewById(R.id.btnAdd);
        etCourse = findViewById(R.id.etCourse);
        rvCourses = findViewById(R.id.rvCourses);
        displayUsername = findViewById(R.id.displayUsername);
        editUser = findViewById(R.id.editUser);

        Bundle extras = getIntent().getExtras();
        mUsername = extras.getString("username");

        mUserDAO = UserDB.getUserDAO(CourseDisplay.this).userDao();
        mUser = mUserDAO.getUsername(mUsername);

        displayUsername.setText("Hello " + " " + mUser.getFName() + "!");

        mCourseDAO = UserDB.getUserDAO(CourseDisplay.this).courseDao();
        List<Course> dbCourses = mCourseDAO.getAllCourses();
        if (dbCourses.size() > 0) {
            for (int i = 0; i < dbCourses.size(); i++) {
                if (dbCourses.get(i).getUsername() == mUsername) {
                    courses.add(dbCourses.get(i).getTitle());
                }
            }
        }else{
            courses.add("Example Course 1");
        }



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddCourseActivity.class);
                i.putExtra("username", mUsername);
                startActivityForResult(i, EDIT_TEXT_CODE);
            }
        });

        courseAdapter = new CourseAdapter(courses);
        rvCourses.setAdapter(courseAdapter);
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == EDIT_TEXT_CODE) {
            Bundle bundle = data.getExtras();
            courses.add(bundle.getString(KEY_COURSE_TEXT));
            courseAdapter.notifyDataSetChanged();
        } else {
            Log.w("CourseDisplay", "Error updating rv");
        }
    }
}