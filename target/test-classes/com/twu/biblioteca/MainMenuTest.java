package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaControl.Biblioteca;
import com.twu.biblioteca.BibliotecaControl.MainMenu;
import com.twu.biblioteca.BibliotecaControl.UserManager;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainMenuTest {

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
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();


    String BOOK_NAME_TEST = "Book Name";
    String MOVIE_NAME_TEST = "Movie Name";
    String USER_ID_TEST = "333-4444";


    // Show Menu Tests
    @Test
    public void shouldShowMenuForLoggedOutCondition(){
        String OUTPUT_EXPECTED =  "\n" +
                "\n" +
                "---- MAIN MENU ---- \n" +
                "(choose an option and insert its number)\n" +
                "\n" +
                "0 - List Books\n" +
                "1 - List Movies\n" +
                "2 - Login\n" +
                "3 - Quit\n";
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);
        when(mockUserManager.isLoggedIn()).thenReturn(false);

        mainMenu.showMenuOptions();

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldShowMenuForLoggedInCondition(){
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        String OUTPUT_EXPECTED =  "\n" +
                "\n" +
                "---- MAIN MENU ---- \n" +
                "(choose an option and insert its number)\n" +
                "\n" +
                "0 - List Books\n" +
                "1 - List Movies\n" +
                "2 - Checkout Book\n" +
                "3 - Return Book\n" +
                "4 - Checkout Movie\n" +
                "5 - Return Movie\n" +
                "6 - User Information\n" +
                "7 - Logout\n" +
                "8 - Quit\n";

        mainMenu.showMenuOptions();

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldHandleInvalidOptionWhenInputNotNumber() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);

        mainMenu.handleUserOption("ytdhfg");
        assertEquals("Select a valid option\n",systemOutRule.getLog());
    }

    @Test
    public void shouldHandleInvalidOptionWhenInputNumberOutOfRange() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);

        mainMenu.handleUserOption("5");
        assertEquals("Select a valid option\n",systemOutRule.getLog());
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

        assertEquals("Books list is empty\n",systemOutRule.getLog());
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

        assertEquals("Movies list is empty\n",systemOutRule.getLog());
    }


    // User Information Tests
    @Test
    public void shouldPrintUserInformation(){
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        String OUTPUT_EXPECTED = "Name: name\nPhone: phone\nEmail: email\n";

        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserInformation()).thenReturn(OUTPUT_EXPECTED);

        mainMenu.handleUserOption("6");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
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


    // Checkout Book Tests
    @Test
    public void shouldHandleCheckoutBookOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);

        mainMenu.handleUserOption("2");

        verify(mockBiblioteca).checkoutBook(BOOK_NAME_TEST,USER_ID_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenCheckoutBookOptionSucceed() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        String OUTPUT_EXPECTED = "\nEnter the name of a book to checkout:\nThank you! Enjoy the book!\n";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutBook(BOOK_NAME_TEST,USER_ID_TEST)).thenReturn(true);

        mainMenu.handleUserOption("2");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutNonexistentBook() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        NoSuchElementException bookDoesNotExist = new NoSuchElementException("Error Message");
        String OUTPUT_EXPECTED = "\nEnter the name of a book to checkout:\n"
                +bookDoesNotExist.getMessage()+
                "\n";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutBook(BOOK_NAME_TEST,USER_ID_TEST)).thenThrow(bookDoesNotExist);

        mainMenu.handleUserOption("2");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutUnavailableBook() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        IllegalArgumentException bookIsNotAvailable = new IllegalArgumentException("different error message");
        String OUTPUT_EXPECTED = "\nEnter the name of a book to checkout:\n"
                +bookIsNotAvailable.getMessage()+
                "\n";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutBook(BOOK_NAME_TEST,USER_ID_TEST)).thenThrow(bookIsNotAvailable);

        mainMenu.handleUserOption("2");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }



    // Checkout Movie Tests
    @Test
    public void shouldHandleCheckoutMovieOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        when(mockScanner.askUserInput()).thenReturn(MOVIE_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);

        mainMenu.handleUserOption("4");

        verify(mockBiblioteca).checkoutMovie(MOVIE_NAME_TEST,USER_ID_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenCheckoutMovieOptionSucceed() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        String OUTPUT_EXPECTED = "\nEnter the name of a movie to checkout:\nThank you! Enjoy the movie!\n";

        when(mockScanner.askUserInput()).thenReturn(MOVIE_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutMovie(MOVIE_NAME_TEST,USER_ID_TEST)).thenReturn(true);

        mainMenu.handleUserOption("4");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutNonexistentMovie() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        NoSuchElementException movieDoesNotExist = new NoSuchElementException("Error Message");
        String OUTPUT_EXPECTED = "\nEnter the name of a movie to checkout:\n"
                +movieDoesNotExist.getMessage()+
                "\n";

        when(mockScanner.askUserInput()).thenReturn(MOVIE_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutMovie(MOVIE_NAME_TEST,USER_ID_TEST)).thenThrow(movieDoesNotExist);

        mainMenu.handleUserOption("4");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutUnavailableMovie() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        IllegalArgumentException movieIsNotAvailable = new IllegalArgumentException("different error message");
        String OUTPUT_EXPECTED = "\nEnter the name of a movie to checkout:\n"
                +movieIsNotAvailable.getMessage()+
                "\n";

        when(mockScanner.askUserInput()).thenReturn(MOVIE_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockUserManager.getUserLoggedID()).thenReturn(USER_ID_TEST);
        when(mockBiblioteca.checkoutMovie(MOVIE_NAME_TEST,USER_ID_TEST)).thenThrow(movieIsNotAvailable);

        mainMenu.handleUserOption("4");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }



    // Return Book Tests
    @Test
    public void shouldHandleReturnBookOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);

        mainMenu.handleUserOption("3");

        verify(mockBiblioteca).returnBook(BOOK_NAME_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenReturnBookOptionSucceed() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);
        String OUTPUT_EXPECTED = "\nEnter the name of a book to return:\nThank you for returning the book!\n";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnBook(BOOK_NAME_TEST)).thenReturn(true);

        mainMenu.handleUserOption("3");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnNonexistentBook() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        NoSuchElementException bookDoesNotExist = new NoSuchElementException("Error Message");
        String OUTPUT_EXPECTED = "\nEnter the name of a book to return:\n"
                +bookDoesNotExist.getMessage()+
                "\n";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnBook(BOOK_NAME_TEST)).thenThrow(bookDoesNotExist);

        mainMenu.handleUserOption("3");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnAvailableBook() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        IllegalArgumentException bookIsNotAvailable = new IllegalArgumentException("Error Message");
        String OUTPUT_EXPECTED = "\nEnter the name of a book to return:\n"
                +bookIsNotAvailable.getMessage()+
                "\n";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnBook(BOOK_NAME_TEST)).thenThrow(bookIsNotAvailable);

        mainMenu.handleUserOption("3");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }



    // Return Movies Tests
    @Test
    public void shouldHandleReturnMovieOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);

        when(mockScanner.askUserInput()).thenReturn(MOVIE_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);

        mainMenu.handleUserOption("5");

        verify(mockBiblioteca).returnMovie(MOVIE_NAME_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenReturnMovieOptionSucceed() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner,mockUserManager);
        String OUTPUT_EXPECTED = "\nEnter the name of a movie to return:\nThank you for returning the movie!\n";

        when(mockScanner.askUserInput()).thenReturn(MOVIE_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnMovie(MOVIE_NAME_TEST)).thenReturn(true);

        mainMenu.handleUserOption("5");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnNonexistentMovie() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        NoSuchElementException movieDoesNotExist = new NoSuchElementException("Error Message");
        String OUTPUT_EXPECTED = "\nEnter the name of a movie to return:\n"
                +movieDoesNotExist.getMessage()+
                "\n";

        when(mockScanner.askUserInput()).thenReturn(MOVIE_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnMovie(MOVIE_NAME_TEST)).thenThrow(movieDoesNotExist);

        mainMenu.handleUserOption("5");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnAvailableMovie() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner, mockUserManager);
        IllegalArgumentException movieIsNotAvailable = new IllegalArgumentException("Error Message");
        String OUTPUT_EXPECTED = "\nEnter the name of a movie to return:\n"
                +movieIsNotAvailable.getMessage()+
                "\n";

        when(mockScanner.askUserInput()).thenReturn(MOVIE_NAME_TEST);
        when(mockUserManager.isLoggedIn()).thenReturn(true);
        when(mockBiblioteca.returnMovie(MOVIE_NAME_TEST)).thenThrow(movieIsNotAvailable);

        mainMenu.handleUserOption("5");

        assertEquals(OUTPUT_EXPECTED,systemOutRule.getLog());
    }
}