package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    String username;
    String password;
    Button loginButton;

    UserDAO mUserDAO;
    User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.etUsername);
        mPassword = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.loginButton);

        mUserDAO = UserDB.getUserDAO(LoginActivity.this).userDao();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValues();
                if(checkForUsername()){
                    if(checkPassword()){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra(username, username);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Welcome " + username + "!",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * gets the values input by app user for username and password
     */
    private void getValues(){
        username = mUsername.getText().toString();
        password = mPassword.getText().toString();
    }

    /**
     * checks if the database has a user with the username
     * @return true - if user is found within the database
     */
    private boolean checkForUsername(){
        mUser = mUserDAO.getUsername(username);
        if(mUser == null){
            Toast.makeText(getApplicationContext(), "User not found.",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    /**
     * checks that the passwords match
     * @return ture - if the password matches the user
     */
    private boolean checkPassword(){
        return mUser.getPassword().equals(password);
    }
}