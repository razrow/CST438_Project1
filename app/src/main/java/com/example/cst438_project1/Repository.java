package com.example.cst438_project1;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private UserDAO userDao;
    private LiveData<List<User>> allUsers;

    public Repository(Application application) {

        UserDB db = UserDB.getUserDAO(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();

    }

    public void insert(User user){
        new LoginInsertion(userDao).execute(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void update(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }
    public void delete(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }
    public void deleteAll(User user) {
        new DeleteAllUsersAsyncTask(userDao).execute(user);
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
    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDao;

        private UpdateUserAsyncTask(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... user) {
            userDao.update(user[0]);
            return null;
        }
    }
    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDao;

        private DeleteUserAsyncTask(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... user) {
            userDao.delete(user[0]);
            return null;
        }
    }
    private static class DeleteAllUsersAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDAO userDao;

        private DeleteAllUsersAsyncTask(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... user) {
            userDao.deleteAllUsers();
            return null;
        }
    }
}
