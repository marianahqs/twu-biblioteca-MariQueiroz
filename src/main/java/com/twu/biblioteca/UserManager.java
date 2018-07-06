package com.twu.biblioteca;

import java.util.List;
import java.util.NoSuchElementException;

public class UserManager {

    private String userLoggedID;
    private List<User> users;

    public UserManager(List<User> users) {
        this.users = users;
    }

    public void login(String userLogginID, String password) throws NoSuchElementException, IllegalArgumentException {
        try {
            User userLoggin = users.stream()
                    .filter(p -> p.getUserID().equals(userLogginID))
                    .findFirst()
                    .get();
            if (userLoggin.getPassword() != password) {
                throw new IllegalArgumentException("That is not a valid password");
            }
            userLoggedID = userLogginID;
        } catch (NoSuchElementException nexc) {
            throw new NoSuchElementException("That user is not valid");
        }
    }

    public boolean isLoggedIn() {
        return userLoggedID != null;
    }

    public void logout(){
        userLoggedID = null;
    }

    public String getUserLoggedID() {
        return userLoggedID;
    }
}
