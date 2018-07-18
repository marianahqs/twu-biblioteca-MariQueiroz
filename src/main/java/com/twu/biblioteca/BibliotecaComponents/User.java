package com.twu.biblioteca.BibliotecaComponents;

import java.util.Objects;

public class User {
    private String userID;
    private String password;
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private UserPrivilege userPrivileges;

    public User(String userID, String password, String name, String phoneNumber, String emailAddress, UserPrivilege userPrivileges) {
        this.userID = userID;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.userPrivileges = userPrivileges;
    }


    @Override
    public String toString(){
        return String.format("Name: %s\nPhone Number: %s\nEmail Address: %s",name,phoneNumber,emailAddress);
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
                emailAddress.equals(otherUser.emailAddress)&&
                userPrivileges.equals(otherUser.userPrivileges);
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

    public UserPrivilege getUserPrivileges() {
        return userPrivileges;
    }

}
