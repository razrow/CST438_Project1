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

public class AddAssignmentActivity extends AppCompatActivity {

    EditText assignmentDescr;
    EditText assignmentMaxScore;
    EditText assignmentEarnedScore;
    AssignmentDAO mAssignmentDAO;
    Button addAssignment;

    String assigmentsDets;
    String inputMax;
    String inputEarned;
    int MaxScore;
    int EarnedScore;
    String mUsername;
    String mCourseTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        assignmentDescr = findViewById(R.id.etDetails);
        assignmentMaxScore = findViewById(R.id.etMaxScore);
        assignmentEarnedScore = findViewById(R.id.etEarnedScore);
        addAssignment = findViewById(R.id.createAssignment);

        mAssignmentDAO = UserDB.getUserDAO(AddAssignmentActivity.this).assignmentDao();
        Bundle extras = getIntent().getExtras();
        mUsername = extras.getString("username");
        mCourseTitle = extras.getString("course title");

        addAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValuesFromDisplay();
                if (assigmentsDets.isEmpty() || // check to see if any fields are empty
                        inputMax.isEmpty() ||
                        inputEarned.isEmpty()) {
                    showEmptyFieldErrorDialog();
                }else{
                    Assignment assignment = new Assignment(assigmentsDets,Integer.parseInt(inputMax),Integer.parseInt(inputEarned),mUsername,mCourseTitle);
                    mAssignmentDAO.insert(assignment);
                    //create intent
                    Intent i = new Intent(getApplicationContext(), CourseDisplay.class);
                    i.putExtra("username", mUsername);
                    i.putExtra(CourseDisplay.KEY_COURSE_TEXT, assignment.getDets());
                    //set result of intent
                    setResult(RESULT_OK, i);
                    Toast.makeText(getApplicationContext(), "Assignment added.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void getValuesFromDisplay(){
        assigmentsDets = assignmentDescr.getText().toString();
        inputMax = assignmentMaxScore.getText().toString();
        inputEarned = assignmentEarnedScore.getText().toString();
    }

    private void showEmptyFieldErrorDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(AddAssignmentActivity.this);
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