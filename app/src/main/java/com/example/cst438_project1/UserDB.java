package com.example.cst438_project1;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class, Grade.class, Enrollment.class, Course.class, Category.class, Assignment.class}, version = 2 )
public abstract class UserDB extends RoomDatabase {

    public static final String DB_NAME = "user_db";
    private static UserDB instance;

    public abstract UserDAO userDao();
    public abstract CourseDAO courseDao();
    public abstract AssignmentDAO assignmentDao();

    public static synchronized UserDB getUserDAO(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()       // needs for Junit testing (version increment)
                    .build();
        }
        return instance;
    }

    /**
     * Populates our database
     */
    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDAO userDao;
        private CourseDAO courseDao;
        private AssignmentDAO assignmentDao;

        private PopulateDbAsyncTask(UserDB db) {
            userDao = db.userDao();
            courseDao = db.courseDao();
            assignmentDao = db.assignmentDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.insert(new User("Tina1", "boyz123", "Tina", "Belcher"));
            courseDao.insert(new Course("Jairo", "Capoeira Class",
                                        "Sexy Dance Fighting", "01/01/01", "03/01/01"));
            assignmentDao.insert(new Assignment("Read 500 books", 100, 100));
            return null;
        }
    }
}
