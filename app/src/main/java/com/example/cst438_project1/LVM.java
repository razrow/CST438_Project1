package com.example.cst438_project1;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LVM extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<User>> getAllUsers;

    public LVM(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        getAllUsers = repository.getAllUsers();

    }

    public void insert(User data) {
        repository.insert(data);
    }

    public LiveData<List<User>> getGetAllData() {
        return getAllUsers;
    }
    
}
