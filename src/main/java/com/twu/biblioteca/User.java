package com.twu.biblioteca;

import java.util.Objects;

public class User {
    private String userID;
    private String password;
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public User(String userID, String password, String name, String phoneNumber, String emailAddress) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString(){
        return String.format("%s \n %s \n %s",name,phoneNumber,emailAddress);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        User otherUser = (User) otherObject;
        return userID.equals(otherUser.userID)&&
                name.equals(otherUser.name) &&
                password.equals(otherUser.password) &&
                phoneNumber.equals(otherUser.phoneNumber)&&
                emailAddress.equals(otherUser.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, password, phoneNumber, emailAddress);
    }


    public String getName() {
        return name;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }
}
