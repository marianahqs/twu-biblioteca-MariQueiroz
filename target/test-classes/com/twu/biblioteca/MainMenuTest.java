package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaControl.Biblioteca;
import com.twu.biblioteca.BibliotecaControl.MainMenu;
import com.twu.biblioteca.BibliotecaControl.UserManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class MainMenuTest {

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Mock
    private Biblioteca mockBiblioteca;

    @Mock
    private UserInputScanner mockScanner;

    @Mock
    private UserManager mockUserManager;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    // Config Print Test
    private PrintStream sysOut;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        sysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void revertStreams() {
        System.setOut(sysOut);
    }

    String ITEM_NAME_TEST = "Book Name";
    String USER_ID_TEST = "333-4444";


    // Show Menu Tests
    @Test
    public void shouldShowMenuForLoggedOutCondition(){
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);
        when(mockUserManager.isLoggedIn()).thenReturn(false);

        mainMenu.showMenuOptions();

        assertThat(outContent.toString(), containsString("0 - List Books\n" +
                "1 - List Movies\n" +
                "2 - Login\n" +
                "3 - Quit"));
    }

    @Test
    public void shouldShowMenuForLoggedInCondition(){
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);
        when(mockUserManager.isLoggedIn()).thenReturn(true);

        mainMenu.showMenuOptions();

        assertThat(outContent.toString(), containsString("0 - List Books\n" +
                "1 - List Movies\n" +
                "2 - Checkout Book\n" +
                "3 - Return Book\n" +
                "4 - Checkout Movie\n" +
                "5 - Return Movie\n" +
                "6 - User Information\n" +
                "7 - Logout\n" +
                "8 - Quit"));
    }

    @Test
    public void shouldHandleInvalidOptionWhenInputNotNumber() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);

        mainMenu.handleUserOption("ytdhfg");
        assertThat(outContent.toString(), containsString("Select a valid option"));
    }

    @Test
    public void shouldHandleInvalidOptionWhenInputNumberOutOfRange() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);

          mainMenu.handleUserOption("5");
         assertThat(outContent.toString(), containsString("Select a valid option"));
    }


    // List Books Tests
    @Test
    public void shouldHandleListBooksOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        mainMenu.handleUserOption("0");

        verify(mockBiblioteca).listBooks();
    }

    @Test
    public void shouldPrintErrorIfBooksListEmpty() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);

        mainMenu.handleUserOption("0");

        assertThat(outContent.toString(), containsString("Books list is empty"));
    }


    // List Movies Tests
    @Test
    public void shouldHandleListMoviesOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        mainMenu.handleUserOption("1");

        verify(mockBiblioteca).listMovies();
    }

    @Test
    public void shouldPrintErrorIfMoviesListEmpty() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);

        mainMenu.handleUserOption("1");

        assertThat(outContent.toString(), containsString("Movies list is empty"));
    }


    // User Information Tests
    @Test
    public void shoudPrintUserInformation(){
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        String OUTPUT_EXPECTED = "Name: name\nPhone: phone\nEmail: email";

        when(mockUserManager.getUserInformation()).thenReturn(OUTPUT_EXPECTED);
        when(mockUserManager.isLoggedIn()).thenReturn(true);

        mainMenu.handleUserOption("6");

        assertThat(outContent.toString(), containsString(OUTPUT_EXPECTED));
    }


    // Login/Logout Tests
    @Test
    public void shouldHandleLogout(){
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        mainMenu.handleUserOption("7");

        verify(mockUserManager).logout();
    }


   @Test
    public void shouldHandleLoginOption(){
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        String ID_TEST = "221-4567";
        String PASSWORD_TEST = "123123";

        when(mockUserManager.isLoggedIn()).thenReturn(false);

        when(mockScanner.askUserInput()).thenReturn(ID_TEST,PASSWORD_TEST);
        mainMenu.handleUserOption("2");
        verify(mockUserManager).login(ID_TEST,PASSWORD_TEST);

    }


    // Checkout Tests
    @Test
    public void shouldHandleCheckoutItemOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        when(mockScanner.askUserInput()).thenReturn(ITEM_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);

        mainMenu.handleUserOption("2");

        verify(mockBiblioteca).checkoutBook(ITEM_NAME_TEST,USER_ID_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenCheckoutItemOptionSucceed() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);

        when(mockScanner.askUserInput()).thenReturn(ITEM_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutBook(ITEM_NAME_TEST,USER_ID_TEST)).thenReturn(true);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString("Thank you! Enjoy the book!"));
    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutNonexistentItem() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        NoSuchElementException bookDoesNotExist = new NoSuchElementException("Error Message");

        when(mockScanner.askUserInput()).thenReturn(ITEM_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutBook(ITEM_NAME_TEST,USER_ID_TEST)).thenThrow(bookDoesNotExist);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString(bookDoesNotExist.getMessage()));
    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutUnavailableItem() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        IllegalArgumentException bookIsNotAvailable = new IllegalArgumentException("different error message");

        when(mockScanner.askUserInput()).thenReturn(ITEM_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutBook(ITEM_NAME_TEST,USER_ID_TEST)).thenThrow(bookIsNotAvailable);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString(bookIsNotAvailable.getMessage()));
    }


    // Return Tests
    @Test
    public void shouldHandleReturnItemOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        when(mockScanner.askUserInput()).thenReturn(ITEM_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);

        mainMenu.handleUserOption("3");

        verify(mockBiblioteca).returnBook(ITEM_NAME_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenReturnItemOptionSucceed() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        when(mockScanner.askUserInput()).thenReturn(ITEM_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnBook(ITEM_NAME_TEST)).thenReturn(true);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString("Thank you for returning the book!"));
    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnNonexistentItem() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        NoSuchElementException bookDoesNotExist = new NoSuchElementException("Error Message");

        when(mockScanner.askUserInput()).thenReturn(ITEM_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnBook(ITEM_NAME_TEST)).thenThrow(bookDoesNotExist);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString(bookDoesNotExist.getMessage()));
    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnItemAvailable() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        IllegalArgumentException bookIsAvailable = new IllegalArgumentException("Error Message");

        when(mockScanner.askUserInput()).thenReturn(ITEM_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnBook(ITEM_NAME_TEST)).thenThrow(bookIsAvailable);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString(bookIsAvailable.getMessage()));
    }
}