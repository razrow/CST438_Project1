package com.example.cst438_project1;
import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


/**
 * Instrumented test, which will execute on an Android device.
 * This tests the User class. Note: I set things back to the original because I kept getting weird
 * errors if i did not do that.
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


    /**
     * Creates the database at the beginning of the test run.
     */
    /*@Before
    public void createDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();;
        userDao = UserDB.getUserDAO(appContext).userDao();

    }*/
    /**
     * Tests inserting an object into the database
     */
    @Test
    public void testInsertDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();;
        userDao = UserDB.getUserDAO(appContext).userDao();
        userDao.deleteAllUsers();
        User insertUser = new User(EXPECTED_USERNAME, EXPECTED_PASSWORD,
                                    EXPECTED_FIRST_NAME, EXPECTED_LAST_NAME);
        userDao.insert(insertUser);
        User testUser = userDao.getUsername(EXPECTED_USERNAME);

        assertEquals("Expected insertUser does not match actual", insertUser, testUser);
    }

    /**
     * This tests that all the getters from the User class are working
     */
    @Test
    public void testGetUserFromDb(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();;
        userDao = UserDB.getUserDAO(appContext).userDao();
        User user = userDao.getUsername(EXPECTED_USERNAME);

        assertEquals("Expected testGetUserFromDb does not match actual",
                                                            EXPECTED_USERNAME, user.getUsername());
        assertEquals("Expected testGetUserFromDb does not match actual",
                                                            EXPECTED_FIRST_NAME, user.getFName());
        assertEquals("Expected testGetUserFromDb does not match actual",
                                                            EXPECTED_LAST_NAME, user.getLName());
        assertEquals("Expected testGetUserFromDb does not match actual",
                                                            EXPECTED_PASSWORD, user.getPassword());
    }

    /**
     * Tests to see if setFirstName from the User class is working along with the @Query in
     * UserDao.
     */
    @Test
    public void testSetFirstName() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();;
        userDao = UserDB.getUserDAO(appContext).userDao();
        userDao.updateFirstName(EXPECTED_USERNAME, UPDATED_FIRST_NAME);
        User user = userDao.getUsername(EXPECTED_USERNAME);
        assertEquals("Expected testSetUserFirstName does not match actual",
                                                            UPDATED_FIRST_NAME, user.getFName());
        // setting back to original
        userDao.updateFirstName(EXPECTED_USERNAME, EXPECTED_FIRST_NAME);
    }

    /**
     * Tests to see if setLastName from the User class is working along with the @Query in
     * UserDao.
     */
    @Test
    public void testSetLastName() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();;
        userDao = UserDB.getUserDAO(appContext).userDao();
        userDao.updateLastName(EXPECTED_USERNAME, UPDATED_LAST_NAME);
        User user = userDao.getUsername(EXPECTED_USERNAME);
        assertEquals("Expected testSetUserLastName does not match actual",
                                                            UPDATED_LAST_NAME, user.getLName());
        // setting back to original
        userDao.updateLastName(EXPECTED_USERNAME, EXPECTED_FIRST_NAME);
    }

    /**
     * Tests to see if setPassword from the User class is working along with the @Query in
     * UserDao.
     */
    @Test
    public void testSetPassword() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();;
        userDao = UserDB.getUserDAO(appContext).userDao();
        userDao.updatePassword(EXPECTED_USERNAME, UPDATED_PASSWORD);
        User user = userDao.getUsername(EXPECTED_USERNAME);
        assertEquals("Expected testSetUserPassword does not match actual",
                                                            UPDATED_PASSWORD, user.getPassword());
        // setting back to original
        userDao.updatePassword(EXPECTED_USERNAME, EXPECTED_PASSWORD);
    }

    /**
     * Tests to see if the delete from the database is working correctly
     */
    @Test
    public  void testDeleteUser() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();;
        userDao = UserDB.getUserDAO(appContext).userDao();
        userDao.delete(userDao.getUsername(EXPECTED_USERNAME));
        User user = userDao.getUsername(EXPECTED_USERNAME);
        assertNull(user);
    }
}