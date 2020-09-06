package com.example.cst438_project1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private UserDAO userDao;
    private LiveData<List<User>> allUsers;

    public Repository(Application application) {

        UserDB db = UserDB.getInstance(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();

    }

    public void insert(User user){
        new LoginInsertion(userDao).execute(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    private static class LoginInsertion extends AsyncTask<User, Void, Void> {

        private UserDAO userDao;

        private LoginInsertion(UserDAO userDao) {

            this.userDao = userDao;

        }

        @Override
        protected Void doInBackground(User... user) {

            userDao.deleteAllUsers();

            userDao.insert(user[0]);
            return null;

        }

    }
}
