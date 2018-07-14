package com.twu.biblioteca.BibliotecaControl;

import com.twu.biblioteca.BibliotecaComponents.User;

import java.util.List;
import java.util.NoSuchElementException;

public class UserManager {

    private String userLoggedID;
    private String userLoggedPrivilege;
    private List<User> users;



    public UserManager(List<User> users) {
        this.users = users;
    }

    public void login(String userLoggedInId, String password) throws NoSuchElementException, IllegalArgumentException {
        try {
            User userLoggedIn = users.stream()
                    .filter(p -> p.getUserID().equals(userLoggedInId))
                    .findFirst()
                    .get();
            if (!password.equals(userLoggedIn.getPassword())) {
                throw new IllegalArgumentException("That is not a valid password");
            }
            userLoggedID = userLoggedInId;
            userLoggedPrivilege = userLoggedIn.getUserPrivileges();
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

    public String getUserInformation() {
        User userLoggedIn = users.stream()
                .filter(p -> p.getUserID().equals(this.userLoggedID))
                .findFirst()
                .get();

        return userLoggedIn.toString();
    }

    public String getUserLoggedPrivilege() {
        if (userLoggedID == null){
            userLoggedPrivilege = null;
        }
        return userLoggedPrivilege;
    }
}
