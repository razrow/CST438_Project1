package com.example.cst438_project1;


import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;



import com.example.cst438_project1.UserDB;
import com.example.cst438_project1.UserDAO;


public class UserTest {
    private static final String[] USER_INFO = {"Jordan", "Doe", "JordanDoe1", "password1"};
    private static final String CORRECT_CONSTRUCTOR_TOSTRING = "username = JordanDoe1\n" +
                                                                "first name = Jordan\n" +
                                                                "last name = Doe\n" +
                                                                "password = password1";

    private UserDAO userDao;
    private UserDB db;

    @Before
    public void createDb() {
        //Context context = androidx.test.core.app.ApplicationProvider.getApplicationContext();

    }


    @Test
    public void testConstructor() {
        User user = new User(USER_INFO[0], USER_INFO[1], USER_INFO[2], USER_INFO[3]);
        assertEquals("Constructor failed", CORRECT_CONSTRUCTOR_TOSTRING, user.toString());
    }

    @Test
    public void testEqualsTrue() {
        User user1 = new User(USER_INFO[0], USER_INFO[1], USER_INFO[2], USER_INFO[3]);
        User user2 = new User(USER_INFO[0], USER_INFO[1], USER_INFO[2], USER_INFO[3]);
        assertEquals("Expected and actual should be TRUE for equals", user1, user2);
    }

    @Test
    public void testEqualsFalse() {
        User user1 = new User(USER_INFO[0], USER_INFO[1], USER_INFO[2], USER_INFO[3]);
        User user2 = new User(USER_INFO[0], USER_INFO[1], USER_INFO[2], "12345");
        assertNotEquals("Expected and actual should be FALSE for equals", user1, user2);
    }

    @Test
    public void testGetters() {
        User user1 = new User(USER_INFO[0], USER_INFO[1], USER_INFO[2], USER_INFO[3]);
        assertEquals("Expected and actual DON'T match", USER_INFO[0], user1.getFName());
        assertEquals("Expected and actual DON'T match", USER_INFO[1], user1.getLName());
        assertEquals("Expected and actual DON'T match", USER_INFO[2], user1.getUsername());
    }

}
