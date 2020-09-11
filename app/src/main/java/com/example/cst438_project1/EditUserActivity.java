package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import java.util.List;


public class EditUserActivity extends AppCompatActivity {


    private LVM editUserViewModel;

    UserDAO userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        // Database context
        userDao = UserDB.getUserDAO(EditUserActivity.this).userDao();

        // Get the current users name to edit




    }
}