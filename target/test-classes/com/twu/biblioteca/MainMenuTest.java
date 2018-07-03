package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

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
    public void shouldHandleListBooksOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);

        mainMenu.handleUserOption("1");

        verify(mockBiblioteca).listBooks();
    }


    // Checkout Tests
    @Test
    public void shouldHandleCheckoutBookOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Book Name";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("2");

        verify(mockBiblioteca).checkoutBook(BOOK_NAME_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenCheckoutBookOptionSucceed() {
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        MainMenu mainMenu = new MainMenu(biblioteca, mockScanner);
        String BOOK_NAME_TEST = "Name Book 2";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString("Thank you! Enjoy the book!"));

    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutNonexistentBook() {
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        MainMenu mainMenu = new MainMenu(biblioteca, mockScanner);
        String BOOK_NAME_TEST = "Not valid book";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString("That is not a valid book name"));

    }

    @Test
    public void shouldPrintErrorMessageWhenTryCheckoutUnavailableBook() {
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        MainMenu mainMenu = new MainMenu(biblioteca, mockScanner);
        String BOOK_NAME_TEST = "Name Book 4";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("2");

        assertThat(outContent.toString(), containsString("That book is not available"));

    }


    // Return Tests
    @Test
    public void shouldHandleReturnBookOption() {
        MainMenu mainMenu = new MainMenu(mockBiblioteca, mockScanner);
        String BOOK_NAME_TEST = "Book Name";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("3");

        verify(mockBiblioteca).returnBook(BOOK_NAME_TEST);
    }

    @Test
    public void shouldPrintOkMessageWhenReturnBookOptionSucceed() {
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        MainMenu mainMenu = new MainMenu(biblioteca, mockScanner);
        String BOOK_NAME_TEST = "Name Book 3";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString("Thank you for returning the book!"));

    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnNonexistentBook() {
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        MainMenu mainMenu = new MainMenu(biblioteca, mockScanner);
        String BOOK_NAME_TEST = "Not valid book";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString("That is not a valid book name"));

    }

    @Test
    public void shouldPrintErrorMessageWhenTryReturnBookAvailable() {
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        MainMenu mainMenu = new MainMenu(biblioteca, mockScanner);
        String BOOK_NAME_TEST = "Name Book 1";

        when(mockScanner.askUserInput()).thenReturn(BOOK_NAME_TEST);

        mainMenu.handleUserOption("3");

        assertThat(outContent.toString(), containsString("That is not a valid book to return"));

    }
}