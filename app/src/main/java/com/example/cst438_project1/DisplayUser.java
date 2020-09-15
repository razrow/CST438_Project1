package com.example.cst438_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DisplayUser extends AppCompatActivity {
    private LVM userViewModel;
    List<Course> courses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_user);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager((new LinearLayoutManager(this)));
//        recyclerView.setHasFixedSize(true);

        //courses = new ArrayLisst<>();
        //courses.add all the courses for the user

        UserAdapter.OnClickListener onClickListener = new UserAdapter.OnClickListener(){
            @Override
            public void onItemClicked(int position) {
                Log.d("DisplayUser", "Single click at position " + position);
                //move to a new activity
                //add stuff to do when they click the course
            }
        };

        UserAdapter adapter = new UserAdapter(courses, onClickListener);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(LVM.class);
        userViewModel.getGetAllData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
//                adapter.setUsers(users);
            }
        });
    }
}