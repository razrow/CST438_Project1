package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourseActivity extends AppCompatActivity {

    EditText instructor;
    EditText title;
    EditText descr;
    EditText sDate;
    EditText eDate;
    Button createCourse;

    String mUsername;
    UserDAO mUserDAO;
    CourseDAO mCourseDAO;

    String courseInstructor;
    String courseTitle;
    String courseDescr;
    String startDate;
    String endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        instructor = findViewById(R.id.etInstructor);
        title = findViewById(R.id.etCourseTitle);
        descr = findViewById(R.id.etDescription);
        sDate = findViewById(R.id.etStartDate);
        eDate = findViewById(R.id.etEndDate);
        createCourse = findViewById(R.id.createCourse);
        mCourseDAO = UserDB.getUserDAO(AddCourseActivity.this).courseDao();

        Bundle extras = getIntent().getExtras();
        mUsername = extras.getString("username");

        createCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if (courseInstructor.isEmpty() || // check to see if any fields are empty
                        courseTitle.isEmpty() ||
                        courseDescr.isEmpty() ||
                        startDate.isEmpty() ||
                        endDate.isEmpty()) {
                    showEmptyFieldErrorDialog();
                }else{
                    Course course = new Course(courseInstructor,courseTitle,courseDescr,startDate,endDate,mUsername);
                    mCourseDAO.insert(course);
                    //create intent
                    Intent i = new Intent(getApplicationContext(), CourseDisplay.class);
                    i.putExtra("username", mUsername);
                    i.putExtra(CourseDisplay.KEY_COURSE_TEXT,course.getTitle());
                    //set result of intent
                    setResult(RESULT_OK,i);
                    Toast.makeText(getApplicationContext(), "Course " + courseTitle + " added.",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

    private void getValuesFromDisplay(){
        courseInstructor = instructor.getText().toString();
        courseTitle = title.getText().toString();
        courseDescr = descr.getText().toString();
        startDate = sDate.getText().toString();
        endDate = eDate.getText().toString();
    }

    /**
     * This method uses AlertDialog builder to display an error that a field is empty
     */
    private void showEmptyFieldErrorDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCourseActivity.this);
        builder.setTitle("Confirm Error");
        builder.setCancelable(true);

        // Inform the user it is incorrect
        builder.setMessage("Field cannot be empty");

        // The user can cancel
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Show the alert
        AlertDialog alert = builder.create();
        alert.show();
    }
}