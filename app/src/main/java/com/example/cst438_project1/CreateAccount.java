package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cst438_project1.UserDB;
import com.example.cst438_project1.UserDAO;

import java.util.List;

public class CreateAccount extends AppCompatActivity {

    //Used for Logcat
    private static final String TAG = "CREATE-ACCOUNT-ACTIVITY" ;

    EditText firstNameEditText;
    EditText lastNameEditText;
    EditText usernameEditText;
    EditText passwordEditText;
    EditText passwordReEntryEditText;
    Button createAccountButton;

    UserDAO userDao;


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

        userDao = UserDB.getUserDAO(CreateAccount.this).userDao();


        // Assign what happens when create account button is clicked
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String passwordReEntry = passwordReEntryEditText.getText().toString();

                if (firstName.isEmpty() || // check to see if any fields are empty
                        lastName.isEmpty() ||
                        username.isEmpty() ||
                        password.isEmpty() ||
                        passwordReEntry.isEmpty()) {

                    showEmptyFieldErrorDialog();
                } else if (!isValidUsername(username)) { // check username to see if valid
                    showUsernameErrorDialog();
                } else if (!isValidPassword(password)) { // check password to see if it's valid
                    showPasswordErrorDialog();
                } else {

                    // checks to see if password matches
                    if (password.equals(passwordReEntry)) {
                        // Check to see if user name is in the database
                        User user = userDao.getUsername(username);
                        if(user == null) {
                            User newUser = new User(username, password, firstName, lastName);
                            userDao.insert(newUser);
                            showSuccessDialog();
                        } else {
                            showDupUsernameError();
                        }
                    } else {
                        showPasswordErrorDialog();
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
        builder.setMessage("Password must match and have at least 1 number");

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
    /**
     * This method uses AlertDialog builder to display an error to the user that their password
     * inputs do not match
     */
    private void showDupUsernameError() {
        // Start building the alert
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateAccount.this);
        builder.setTitle("Confirm Error");
        builder.setCancelable(true);

        // Inform the user it is incorrect
        builder.setMessage("Username Taken");

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