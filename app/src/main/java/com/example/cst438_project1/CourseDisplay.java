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
    List<Course> dbCourses;
    Button btnAdd;
    RecyclerView rvCourses;
    CourseAdapter courseAdapter;
    String mUsername = "placeholder";
    TextView displayUsername;
    Button editUser;
    UserDAO mUserDAO;
    CourseDAO mCourseDAO;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_display);

        btnAdd = findViewById(R.id.btnAdd);
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
                if (dbCourses.get(i).getUsername().equals(mUsername)) {
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

        CourseAdapter.OnClickListener onClickListener = new CourseAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(CourseDisplay.this, AssignmentDisplayActivity.class);
                intent.putExtra("course title", courses.get(position));
                intent.putExtra("username", mUsername);
                startActivity(intent);
            }
        };

        CourseAdapter.OnLongClickListener onLongClickListener = new CourseAdapter.OnLongClickListener(){
            @Override
            public void onItemLongClicked(int position) {
                Intent intent = new Intent(CourseDisplay.this, CourseEditActivity.class);
                intent.putExtra("course title", courses.get(position));
                intent.putExtra("username", mUsername);
                startActivityForResult(intent,EDIT_TEXT_CODE);
            }
        };

        courseAdapter = new CourseAdapter(courses,onLongClickListener, onClickListener);
        rvCourses.setAdapter(courseAdapter);
        rvCourses.setLayoutManager(new LinearLayoutManager(this));


        editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editUserIntent = new Intent(getApplicationContext(), EditUserActivity.class);
                editUserIntent.putExtra("username", mUsername);
                startActivity(editUserIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == EDIT_TEXT_CODE) {
//            Bundle bundle = data.getExtras();
//            courses.add(bundle.getString(KEY_COURSE_TEXT));
            courses.clear();
            List<Course> dbCourses = mCourseDAO.getAllCourses();
            if (dbCourses.size() > 0) {
                for (int i = 0; i < dbCourses.size(); i++) {
                    if (dbCourses.get(i).getUsername().equals(mUsername)) {
                        courses.add(dbCourses.get(i).getTitle());
                    }
                }
            }
            courseAdapter.notifyDataSetChanged();
        } else {
            Log.w("CourseDisplay", "Error updating rv");
        }
    }
}