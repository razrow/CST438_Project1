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

import java.util.List;


public class EditUserActivity extends AppCompatActivity {

    private LVM editUserViewModel;

    UserDAO userDao;

    private TextView welcomeTextView;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText passwordEditText;
    private Button saveButton;

    private String updateFirstName;
    private String updatedLastName;
    private String updatedPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        welcomeTextView = findViewById(R.id.editWelcomeTextView);
        firstNameEditText = findViewById(R.id.editFirstEditText);
        lastNameEditText = findViewById(R.id.editLastEditText);
        passwordEditText = findViewById(R.id.editPasswordEditText);
        saveButton = findViewById(R.id.editSaveButton);

        // Database context
        userDao = UserDB.getUserDAO(EditUserActivity.this).userDao();
        final String username = getIntent().getStringExtra("USERNAME");
        User currentUser = userDao.getUsername(username);

        // Set fields up for editing
        welcomeTextView.setText("Editing " + username);
        firstNameEditText.setText(currentUser.getFName());
        lastNameEditText.setText(currentUser.getLName());
        passwordEditText.setText(currentUser.getPassword());

        // Button clicked we gotta save the info!
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedFirstName = firstNameEditText.getText().toString();
                String updatedLastName = lastNameEditText.getText().toString();
                String updatedPassword = passwordEditText.getText().toString();

                userDao.updateFirstName(username, updatedFirstName);
                userDao.updateLastName(username, updatedLastName);
                userDao.updatePassword(username, updatedPassword);

            }
        });


    }
    /**
     * Displays the user's information was saved
     */
    private void showSuccessDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(EditUserActivity.this);
        builder.setTitle("Changes Saved");
        builder.setCancelable(true);

        // Inform the user it is correct
        builder.setMessage(R.string.savedMessage);


        // The user can cancel
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(EditUserActivity.this, MainActivity.class);
                startActivity(intent);
                dialog.cancel();
            }
        });

        // Show the alert
        AlertDialog alert = builder.create();
        alert.show();
    }
}