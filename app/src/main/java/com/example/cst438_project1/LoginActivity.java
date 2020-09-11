package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText mUsername;
    EditText mPassword;
    String username;
    String password;
    Button loginButton;

    UserDAO mUserDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.etUsername);
        mPassword = findViewById(R.id.etPassword);
        loginButton = findViewById(R.id.loginButton);

        mUserDAO = UserDB.getInstance(LoginActivity.this).userDao();

    }


    private void getValues(){
        username = mUsername.getText().toString();
        password = mPassword.getText().toString();
    }
}