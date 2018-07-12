package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaControl.UserManager;
import com.twu.biblioteca.Components.User;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class UserManagerTest {
    List<User> USERS = List.of(new User("123-4567", "123abc", "Fulana de tal", "2343-3434","fulana@mail.com"),
            new User("222-3456", "password", "Ze das Couves", "3333-4456", "zeze@email.com"),
            new User("345-6789", "password1234", "Mari", "6565-0099","mari@email.com"));
    UserManager userManager = new UserManager(USERS);

    @Test
    public void shouldUpdateLoggedInFlagAndUserIDWhenLogin() {
        userManager.login("222-3456", "password");
        assertTrue(userManager.isLoggedIn());
        assertEquals("222-3456",userManager.getUserLoggedID());
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsWrong() {
        IllegalArgumentException testedException = assertThrows(IllegalArgumentException.class,
                () -> {
                    userManager.login("222-3456", "xxxx");
                });
        assertEquals("That is not a valid password",testedException.getMessage());
        assertFalse(userManager.isLoggedIn());
    }

    @Test
    public void shouldReturnErrorWhenUserInvalid() {
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    userManager.login("555-3456", "xxxx");
                });
        assertEquals("That user is not valid",testedException.getMessage());
        assertFalse(userManager.isLoggedIn());
    }

    @Test
    public void shouldUpdateLoggedInFlagAndUserIDWhenLogout() {
        userManager.login("222-3456", "password");
        assertTrue(userManager.isLoggedIn());
        assertEquals("222-3456",userManager.getUserLoggedID());

        userManager.logout();
        assertFalse(userManager.isLoggedIn());
        assertEquals(null,userManager.getUserLoggedID());
    }

    @Test
    public void shouldReturnUserInformation(){
        userManager.login("222-3456", "password");
        assertEquals("Name: Ze das Couves\nPhone Number: 3333-4456\nEmail Address: zeze@email.com",userManager.getUserInformation());
    }
}
