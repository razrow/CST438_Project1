package com.example.cst438_project1;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;



/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UserInstrumentedTest {

    public static final String EXPECTED_USERNAME = "Bob1";
    public static final String EXPECTED_FIRST_NAME = "Bob";
    public static final String EXPECTED_LAST_NAME = "Belcher";
    public static final String EXPECTED_PASSWORD = "burger123";

    public static final String UPDATED_USERNAME = "MustasheBob";
    public static final String UPDATED_FIRST_NAME = "Bobby";
    public static final String UPDATED_LAST_NAME = "Belchery";
    public static final String UPDATED_PASSWORD = "fat123";
    private UserDAO userDao;



    @Before
    public void createDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        //db = Room.inMemoryDatabaseBuilder(appContext, UserDB.class).build();
        //userDao = db.getInstance(appContext);
        //userDao = Room.databaseBuilder(appContext, UserDB.class, "user_db").allowMainThreadQueries().build();
        userDao = UserDB.getUserDAO(appContext).userDao();

    }
    @Test
    public void testInsertDb(){
        userDao.deleteAllUsers();
        User insertUser = new User(EXPECTED_USERNAME, EXPECTED_PASSWORD, EXPECTED_FIRST_NAME, EXPECTED_LAST_NAME);
        userDao.insert(insertUser);
        User testUser = userDao.getUsername(EXPECTED_USERNAME);

        assertEquals("Expected insertUser does not match actual", insertUser, testUser);
    }

    @Test
    public void testGetUserFromDb(){
        User user = userDao.getUsername(EXPECTED_USERNAME);

        assertEquals("Expected testGetUserFromDb does not match actual", EXPECTED_USERNAME, user.getUsername());
        assertEquals("Expected testGetUserFromDb does not match actual", EXPECTED_FIRST_NAME, user.getFName());
        assertEquals("Expected testGetUserFromDb does not match actual", EXPECTED_LAST_NAME, user.getLName());
        assertEquals("Expected testGetUserFromDb does not match actual", EXPECTED_PASSWORD, user.getPassword());
    }

    @Test
    public void testSetFirstName() {
        userDao.updateFirstName(EXPECTED_USERNAME, UPDATED_FIRST_NAME);
        User user = userDao.getUsername(EXPECTED_USERNAME);
        assertEquals("Expected testSetUserFirstName does not match actual", UPDATED_FIRST_NAME, user.getFName());
        // setting back to original
        userDao.updateFirstName(EXPECTED_USERNAME, EXPECTED_FIRST_NAME);
    }

    @Test
    public void testSetLastName() {
        userDao.updateLastName(EXPECTED_USERNAME, UPDATED_LAST_NAME);
        User user = userDao.getUsername(EXPECTED_USERNAME);
        assertEquals("Expected testSetUserLastName does not match actual", UPDATED_LAST_NAME, user.getLName());
        // setting back to original
        userDao.updateLastName(EXPECTED_USERNAME, EXPECTED_FIRST_NAME);
    }

    @Test
    public void testSetPassword() {
        userDao.updatePassword(EXPECTED_USERNAME, UPDATED_PASSWORD);
        User user = userDao.getUsername(EXPECTED_USERNAME);
        assertEquals("Expected testSetUserPassword does not match actual", UPDATED_PASSWORD, user.getPassword());
        // setting back to original
        userDao.updatePassword(EXPECTED_USERNAME, EXPECTED_PASSWORD);
    }
    @Test
    public  void testDeleteUser() {
        userDao.delete(userDao.getUsername(EXPECTED_USERNAME));
        User user = userDao.getUsername(EXPECTED_USERNAME);
        assertNull(user);
    }
}