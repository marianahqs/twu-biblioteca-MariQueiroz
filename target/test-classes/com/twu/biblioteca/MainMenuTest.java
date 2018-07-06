package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainMenuTest {

    @Mock
    private Biblioteca mockBiblioteca;

    @Mock
    private UserInputScanner mockScanner;

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


    @Test
    public void shouldHandleInvalidOption() {

        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);

        mainMenu.handleUserOption("5");

        assertThat(outContent.toString(), containsString("Select a valid option"));
    }


    // List Books Tests
    @Test
    public void shouldHandleListBooksOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);

        mainMenu.handleUserOption("1");

        verify(mockBiblioteca).listBooks();
    }

    @Test
    public void shouldFormatListOfBooks() {
        List<Item> BOOK_LIST =  List.of(new Item("small", "author small", 1988, true),
                new Item("big","author", 1987, true),
                new Item("really big name", "author", 1987, true));
        Biblioteca biblioteca = new Biblioteca(BOOK_LIST);
        MainMenu mainMenu = new MainMenu(biblioteca, mockScanner);

        mainMenu.handleUserOption("1");

        assertThat(outContent.toString(), containsString("small               author small     1988\n" +
                "big                 author           1987\n" +
                "really big name     author           1987"));
    }

    @Test
    public void shouldPrintErrorIfBooksLiostEmpty() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);

        mainMenu.handleUserOption("1");

        assertThat(outContent.toString(), containsString("Items list is empty"));
    }


    // Checkout Tests
    @Test
    public void shouldHandleCheckoutBookOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Item Name";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("2");

        verify(mockBiblioteca).checkoutBook(BOOK_NAME_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenCheckoutBookOptionSucceed() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Name Item 2";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockBiblioteca.checkoutBook(BOOK_NAME_TEST)).thenReturn(true);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString("Thank you! Enjoy the book!"));
    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutNonexistentBook() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Not valid book";
        NoSuchElementException bookDoesNotExist = new NoSuchElementException("Error Message");

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockBiblioteca.checkoutBook(BOOK_NAME_TEST)).thenThrow(bookDoesNotExist);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString(bookDoesNotExist.getMessage()));
    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutUnavailableBook() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Name Item 4";
        IllegalArgumentException bookIsNotAvailable = new IllegalArgumentException("different error message");

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockBiblioteca.checkoutBook(BOOK_NAME_TEST)).thenThrow(bookIsNotAvailable);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString(bookIsNotAvailable.getMessage()));
    }


    // Return Tests
    @Test
    public void shouldHandleReturnBookOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Item Name";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("3");

        verify(mockBiblioteca).returnBook(BOOK_NAME_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenReturnBookOptionSucceed() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Name Item 3";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockBiblioteca.returnBook(BOOK_NAME_TEST)).thenReturn(true);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString("Thank you for returning the book!"));
    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnNonexistentBook() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Not valid book";
        NoSuchElementException bookDoesNotExist = new NoSuchElementException("Error Message");

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockBiblioteca.returnBook(BOOK_NAME_TEST)).thenThrow(bookDoesNotExist);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString(bookDoesNotExist.getMessage()));
    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnBookAvailable() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Name Item 1";
        IllegalArgumentException bookIsAvailable = new IllegalArgumentException("Error Message");

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);
        when(mockBiblioteca.returnBook(BOOK_NAME_TEST)).thenThrow(bookIsAvailable);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString(bookIsAvailable.getMessage()));
    }
}