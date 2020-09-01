package com.example.cst438_project1;

//import androidx.room.Entity;
//import androidx.room.PrimaryKey;
//@Entity(tableName = "user_table")

import java.util.Objects;

public class User {
    //@PrimaryKey(autoGenerate = true)
    private int userId;

    private String firstName;
    private String lastName;
    private String username;
    private String password;


    /**
     * Constructor
     * @param firstName - String a person's first name
     * @param lastName - String a person's last name
     * @param username - String a person's username
     * @param password - String a password

     */
    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username) &&
                password.equals(user.password) &&
                firstName.equals(user.firstName) &&
                lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, firstName, lastName);
    }


    @Override
    public String toString() {
        return "username = " + username + "\n" +
                "first name = " + firstName + "\n" +
                "last name = " + lastName + "\n" +
                "password = " + password;
    }


    /**
     * UserId getter
     * @return int - represent the user's id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Username getter
     * @return String - represents the user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Password getter
     * @return String - representing the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * First name getter
     * @return String - representing the user's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Last name getter
     * @return String - represeting the user's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Username setter
     * @param username - String of the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Password setter
     * @param password - String of the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * First name setter
     * @param firstName - String of person's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last name setter
     * @param lastName - String of person's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}
