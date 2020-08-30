package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccount extends AppCompatActivity {

    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText passwordReEntryEditText;
    Button createAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // Initialize variables
        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordReEntryEditText = findViewById(R.id.passwordReEnterEditText);
        createAccountButton = findViewById(R.id.createAccountButton);

        // Assign what happens when create account button is clicked
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstNameEditText.getText().toString().isEmpty() || // check to see if any fields are empty
                        lastNameEditText.getText().toString().isEmpty() ||
                        usernameEditText.getText().toString().isEmpty() ||
                        passwordEditText.getText().toString().isEmpty() ||
                        passwordReEntryEditText.getText().toString().isEmpty()) {

                    showEmptyFieldErrorDialog();
                } else if (!isValidUsername(usernameEditText.getText().toString())) { // check username to see if valid
                    // TODO : need to check database to see if username exists
                    showUsernameErrorDialog();
                } else if (!isValidPassword(passwordEditText.getText().toString())) { // check password to see if it's valid
                    showPasswordErrorDialog();
                } else {
                    // checks to see if password matches
                    if(passwordEditText.getText().toString().equals(passwordReEntryEditText.getText().toString())) {
                        showSuccessDialog();
                    }
                }

            }
        });
    }

    /**
     * Wrapper method that checks if the username and password EditText has at least 3 alpha
     * and one number
     * @param username - The users input for a username
     * @param password - The users input for a password corresponding with the user name
     * @return true - if it is a valid account creation
     */
    private boolean isValidAccountCreation(String username, String password) {
        return isValidUsername(username) && isValidPassword(password);
    }
    /**
     * Checks to see if the username is a correct format
     * @param username - the users input for a username
     * @return true - if the username has 3 alpha and one number
     */
    private boolean isValidUsername(String username) {
        return hasAlpha(username) && hasNumbers(username);
    }

    /**
     * Checks to see if the password is correct format
     * @param password - the users input for a password
     * @return true - if the password has 3 alpha and one number
     */
    private boolean isValidPassword(String password) {
        return hasAlpha(password) && hasNumbers(password);
    }

    /**
     * Checks to see if a string has at least 3 alpha characters in it
     * @param inputString - the target string that will be checked
     * @return - true if the string has 3 alpha characters
     */
    private boolean hasAlpha(String inputString) {
        int count = 0;
        boolean result = false;
        for (int i = 0; i < inputString.length(); i++) {
            if (Character.isAlphabetic(inputString.charAt(i))) {
                count++;
            }
        }
        if(count >= 3) {
            result = true;
        }
        return result;
    }
    /**
     * Checks to see if a String has at least 1 number in it
     * @param inputString - the target String that will be checked
     * @return true - if the String has 1 number
     */
    private boolean hasNumbers(String inputString) {
        int count = 0;
        boolean result = false;
        for (int i = 0; i < inputString.length(); i++) {
            if (Character.isDigit(inputString.charAt(i))) {
                count++;
            }
        }
        if (count >= 1) {
            result = true;
        }
        return result;

    }

    /**
     * Displays the user that their account was successfully created.
     */
    private void showSuccessDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccount.this);
        builder.setTitle("Account Successfully Created");
        builder.setCancelable(true);

        // Inform the user it is incorrect
        builder.setMessage(R.string.accountCreatedMessage);


        // The user can cancel
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(CreateAccount.this, MainActivity.class);
                startActivity(intent);
                dialog.cancel();
            }
        });

        // Show the alert
        AlertDialog alert = builder.create();
        alert.show();

    }

    /**
     * Displays to the user that the username they are trying to use is taken.
     */
    private void showUsernameErrorDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccount.this);
        builder.setTitle("Confirm Error");
        builder.setCancelable(true);

        // Inform the user it is incorrect
        builder.setMessage("User name is taken");

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
    /**
     * This method uses AlertDialog builder to display an error to the user that their password
     * inputs do not match
     */
    private void showPasswordErrorDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccount.this);
        builder.setTitle("Confirm Error");
        builder.setCancelable(true);

        // Inform the user it is incorrect
        builder.setMessage("Password must match");

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
    /**
     * This method uses AlertDialog builder to display an error that a field is empty
     */
    private void showEmptyFieldErrorDialog() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccount.this);
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