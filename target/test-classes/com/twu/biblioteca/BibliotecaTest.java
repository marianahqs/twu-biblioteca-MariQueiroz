package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class BibliotecaTest {

    //List Books
    @Test
    public void shouldReturnTheListOfBooks() {
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 2018, true),
                new Book("Name Book 2", "Author Book 2", 2017, true));

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals(books, biblioteca.listBooks());
    }

    @Test
    public void shouldReturnOnlyAvailableBook(){
        List<Book> availableBooks = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true));
        List<Book> unavailableBooks = List.of(new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        List<Book> books = new ArrayList<>(availableBooks);
        books.addAll(unavailableBooks);

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals(availableBooks, biblioteca.listBooks());
    }


    //Checkout Books
    @Test
    public void shouldReturnTrueWhenCheckoutSucceed(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        assertTrue(biblioteca.checkoutBook("Name Book 1"));
    }

    @Test
    public void shouldReturnListWithoutCheckedOutBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        List<Book> expectedReturnList = List.of(new Book("Name Book 1", "Author Book 1", 1980, true));

        Biblioteca biblioteca = new Biblioteca(books);

        biblioteca.checkoutBook("Name Book 2");
        assertEquals(expectedReturnList, biblioteca.listBooks());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryCheckoutNonexistentBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.checkoutBook("Not In The List");
                });

        assertEquals("That is not a valid book name",testedException.getMessage());

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryCheckoutUnavailableBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        IllegalArgumentException testedException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.checkoutBook("Name Book 4");
                });

        assertEquals("That book is not available",testedException.getMessage());

    }



    //Return Books
    @Test
    public void shouldReturnTrueWhenReturnSucceed(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        assertTrue(biblioteca.returnBook("Name Book 3"));
    }

    @Test
    public void shouldReturnListWithReturnedBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        List<Book> expectedReturnList = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 4", "Author Book 4", 1999, true));

        Biblioteca biblioteca = new Biblioteca(books);

        biblioteca.returnBook("Name Book 4");
        assertEquals(expectedReturnList, biblioteca.listBooks());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryReturnNonexistentBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.returnBook("Not In The List");
                });

        assertEquals("That is not a valid book name",testedException.getMessage());

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryReturnBookAvailable(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        IllegalArgumentException testedException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.returnBook("Name Book 1");
                });

        assertEquals("That is not a valid book to return",testedException.getMessage());

    }

}

