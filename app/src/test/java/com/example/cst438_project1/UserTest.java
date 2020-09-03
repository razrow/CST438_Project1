package com.example.cst438_project1;

import org.junit.Test;
import static org.junit.Assert.*;
public class UserTest {
    private static final String[] USER_INFO = {"Jordan", "Doe", "JordanDoe1", "password1"};
    private static final String CORRECT_CONSTRUCTOR_TOSTRING = "username = JordanDoe1\n" +
                                                                "first name = Jordan\n" +
                                                                "last name = Doe\n" +
                                                                "password = password1";
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
        assertEquals("Expected and actual DON'T match", USER_INFO[0], user1.getFirstName());
        assertEquals("Expected and actual DON'T match", USER_INFO[1], user1.getLastName());
        assertEquals("Expected and actual DON'T match", USER_INFO[2], user1.getUsername());
    }

}
