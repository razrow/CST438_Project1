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

public class AssignmentDisplayActivity extends AppCompatActivity {

    public static final String KEY_COURSE_TEXT = "course_text";
    public static final String KEY_COURSE_POSITION = "course_position";
    public static final int EDIT_TEXT_CODE = 20;

    List<Double> assignments = new ArrayList<>();
    Button btnAdd;
    RecyclerView rvAssignments;
    AssignmentAdapter assignmentAdapter;
    String mUsername = "placeholder";
    String mCourseTitle = "placeholder";
    TextView displayUsername;
    Button editUser;
    AssignmentDAO mAssignmentDAO;
    UserDAO mUserDAO;
    CourseDAO mCourseDAO;
    User mUser;
    Course mCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_display);

        btnAdd = findViewById(R.id.btnAdd);
        rvAssignments = findViewById(R.id.rvAssignments);
        displayUsername = findViewById(R.id.displayUsername);
        editUser = findViewById(R.id.editUser);

        Bundle extras = getIntent().getExtras();
        mUsername = extras.getString("username");
        mCourseTitle = extras.getString("course title");

        mUserDAO = UserDB.getUserDAO(AssignmentDisplayActivity.this).userDao();
        mUser = mUserDAO.getUsername(mUsername);
        mCourseDAO = UserDB.getUserDAO(AssignmentDisplayActivity.this).courseDao();
        mCourse = mCourseDAO.getCourse(mCourseTitle);

        displayUsername.setText("Hello " + " " + mUser.getFName() + ", showing assignments for ");

        mAssignmentDAO = UserDB.getUserDAO(AssignmentDisplayActivity.this).assignmentDao();
        List<Assignment> dbAssignments = mAssignmentDAO.getAllAssignments();
        if (dbAssignments.size() > 0) {
            for (int i = 0; i < dbAssignments.size(); i++) {
                if (dbAssignments.get(i).getUsername() == mUsername) {
                    assignments.add(Double.valueOf(dbAssignments.get(i).getEarnedScore())/Double.valueOf(dbAssignments.get(i).getMaxScore()));
                }
            }
        }else{
            assignments.add(Double.valueOf(0));
        }



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AddAssignmentActivity.class);
                i.putExtra("username", mUsername);
                startActivityForResult(i, EDIT_TEXT_CODE);
            }
        });

        AssignmentAdapter.OnClickListener onClickListener = new AssignmentAdapter.OnClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(AssignmentDisplayActivity.this, EditAssignmentActivity.class);
                intent.putExtra("assignment score", assignments.get(position));
                intent.putExtra("username", mUsername);
                startActivity(intent);
            }
        };

        assignmentAdapter = new AssignmentAdapter(assignments, onClickListener);
        rvAssignments.setAdapter(assignmentAdapter);
        rvAssignments.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == EDIT_TEXT_CODE) {
            Bundle bundle = data.getExtras();
            assignments.add(bundle.getDouble(KEY_COURSE_TEXT));
            assignmentAdapter.notifyDataSetChanged();
        } else {
            Log.w("CourseDisplay", "Error updating rv");
        }
    }
}