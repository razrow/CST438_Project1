package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditAssignmentActivity extends AppCompatActivity {

    AssignmentDAO assignmentDao;
    UserDAO userDao;

    private TextView editAssignmentTextView;
    private EditText editDetailsEditText;
    private EditText editMaxScoreEditText;
    private EditText editEarnedScoreEditText;
    private Button saveButton;

    private int assignmentId;
    private String updatedDetails;
    private int updatedMaxScore;
    private int updatedEarnedScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_assignment);

        editAssignmentTextView = findViewById(R.id.editAssignmentTextView);
        editDetailsEditText = findViewById(R.id.editDetailEditText);
        editMaxScoreEditText = findViewById(R.id.editMaxScoreEditView);
        editEarnedScoreEditText = findViewById(R.id.editEarnedScoreEditText);
        saveButton = findViewById(R.id.editAssignmentSaveButton);

        // Database context
        userDao = UserDB.getUserDAO(EditAssignmentActivity.this).userDao();
        assignmentDao = UserDB.getUserDAO(EditAssignmentActivity.this).assignmentDao();

        // TODO we need to get the assignment ID somehow. For now i'm setting it to 1
        assignmentId = 1;

        String username = getIntent().getStringExtra("USERNAME");
        Assignment currentAssignment = assignmentDao.getAssignmentById(assignmentId);

        // Setting fields up for edditing
        editAssignmentTextView.setText("Editing Assignment #" + assignmentId);
        editDetailsEditText.setText(currentAssignment.getDets());
        editMaxScoreEditText.setText(currentAssignment.getMaxScore());
        editEarnedScoreEditText.setText(currentAssignment.getEarnedScore());

        // Button clicked to save!
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatedDetails = editDetailsEditText.getText().toString();

                try {
                    updatedMaxScore = Integer.parseInt(editMaxScoreEditText.getText().toString());
                    updatedEarnedScore = Integer.parseInt(editEarnedScoreEditText.getText().toString());
                } catch (NumberFormatException nfe) {
                    editMaxScoreEditText.setError("We need this to be a number");
                    editEarnedScoreEditText.setError("We need this to be a number");

                }
                assignmentDao.updateDetails(assignmentId, updatedDetails);
                assignmentDao.updateMaxScore(assignmentId, updatedMaxScore);
                assignmentDao.updateEarnedScore(assignmentId, updatedEarnedScore);
                
                showSuccessDialog();
            }
        });
    }

    /**
     * Displays the user's information was saved
     */
    private void showSuccessDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(EditAssignmentActivity.this);
        builder.setTitle("Changes Saved");
        builder.setCancelable(true);

        // Inform the user it is correct
        builder.setMessage(R.string.savedMessage);


        // The user can cancel
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(EditAssignmentActivity.this, MainActivity.class);
                startActivity(intent);
                dialog.cancel();
            }
        });

        // Show the alert
        AlertDialog alert = builder.create();
        alert.show();
    }
}