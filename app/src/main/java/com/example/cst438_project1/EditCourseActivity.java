package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditCourseActivity extends AppCompatActivity {

    CourseDAO courseDAO;
    UserDAO userDao;

    private TextView editCourseTextView;
    private EditText editDetailEditText;
    private EditText editInstructorEditText;
    private EditText editTitleEditText;
    private EditText editDescriptionEditText;
    private Button saveButton;

    private int courseId;
    private String updatedDetails;
    private String updatedInstructor;
    private String updatedTitle;
    private String updatedDescription;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);

        editCourseTextView = findViewById(R.id.editCourseTextView);
        editDetailEditText = findViewById(R.id.editDetailEditText);
        editInstructorEditText = findViewById(R.id.editInstructorEditView);
        editTitleEditText = findViewById(R.id.editTitleEditText);
        editDescriptionEditText = findViewById(R.id.editDescriptionEditText);
        saveButton = findViewById(R.id.editCourseSaveButton);

        // Database context
        userDao = UserDB.getUserDAO(EditCourseActivity.this).userDao();
        courseDAO = UserDB.getUserDAO(EditCourseActivity.this).courseDao();

        // TODO we need to get the course ID somehow. For now i'm setting it to 1
        courseId = 1;

        String username = getIntent().getStringExtra("USERNAME");
        Course currentCourse = courseDAO.getCourseById(courseId);

        editCourseTextView.setText("Editing Course #" + courseId);
        editTitleEditText.setText(currentCourse.getTitle());
        editDescriptionEditText.setText(currentCourse.getDescription());
        editInstructorEditText.setText(currentCourse.getInstructor());

        // Button clicked to save!
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedInstructor = editInstructorEditText.getText().toString();
                updatedDescription = editDescriptionEditText.getText().toString();
                updatedTitle = editTitleEditText.getText().toString();

                courseDAO.updateDescription(courseId, updatedDescription);
                courseDAO.updateInstructor(courseId, updatedInstructor);
                courseDAO.updateTitle(courseId, updatedTitle);
            }
        });
    }

    /**
     * Displays the user's information was saved
     */
    private void showSuccessDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(EditCourseActivity.this);
        builder.setTitle("Changes Saved");
        builder.setCancelable(true);

        // Inform the user it is correct
        builder.setMessage(R.string.savedMessage);


        // The user can cancel
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(EditCourseActivity.this, MainActivity.class);
                startActivity(intent);
                dialog.cancel();
            }
        });

        // Show the alert
        AlertDialog alert = builder.create();
        alert.show();
    }
}