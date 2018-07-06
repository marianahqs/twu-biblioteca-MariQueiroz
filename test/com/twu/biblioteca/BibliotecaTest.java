package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class BibliotecaTest {
    List<Item> BOOKS = List.of(new Item("Name Item 1", "Author Item 1", 1980, true),
            new Item("Name Item 2", "Author Item 2",2003,true),
            new Item("Name Item 3", "Author Item 3", 2017, false),
            new Item("Name Item 4","Author Item 4",1999,false));
    Biblioteca biblioteca = new Biblioteca(BOOKS);

    @Test
    public void shouldUpdateUserLoggedInInformation (){
//        Biblioteca biblioteca = new Biblioteca();

    }

    //List Books
    @Test
    public void shouldReturnTheListOfBooks() {
        List<Item> BOOKS_ALL_AVAILABLE = List.of(new Item("Name Item 1", "Author Item 1", 2018, true),
                new Item("Name Item 2", "Author Item 2", 2017, true));

        Biblioteca biblioteca = new Biblioteca(BOOKS_ALL_AVAILABLE);

        assertEquals(BOOKS_ALL_AVAILABLE, biblioteca.listBooks());
    }

    @Test
    public void shouldReturnOnlyAvailableBook(){
        List<Item> EXPECTED_RETURN_LIST = List.of(new Item("Name Item 1", "Author Item 1", 1980, true),
                new Item("Name Item 2", "Author Item 2",2003,true));

        assertEquals(EXPECTED_RETURN_LIST, biblioteca.listBooks());
    }


    //Checkout Books
    @Test
    public void shouldReturnTrueWhenCheckoutSucceed(){
        assertTrue(biblioteca.checkoutBook("Name Item 1"));
    }

    @Test
    public void shouldReturnListWithoutCheckedOutBook(){
        List<Item> EXPECTED_RETURN_LIST = List.of(new Item("Name Item 1", "Author Item 1", 1980, true));

        biblioteca.checkoutBook("Name Item 2");
        assertEquals(EXPECTED_RETURN_LIST, biblioteca.listBooks());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryCheckoutNonexistentBook(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.checkoutBook("Not In The List");
                });

        assertEquals("That is not a valid book name",testedException.getMessage());

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryCheckoutUnavailableBook(){
        IllegalArgumentException testedException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.checkoutBook("Name Item 4");
                });

        assertEquals("That book is not available",testedException.getMessage());

    }


    //Return Books
    @Test
    public void shouldReturnTrueWhenReturnSucceed(){
        assertTrue(biblioteca.returnBook("Name Item 3"));
    }

    @Test
    public void shouldReturnListWithReturnedBook(){
        List<Item> EXPECTED_RETURN_LIST = List.of(new Item("Name Item 1", "Author Item 1", 1980, true),
                new Item("Name Item 2", "Author Item 2",2003,true),
                new Item("Name Item 4", "Author Item 4", 1999, true));

        biblioteca.returnBook("Name Item 4");
        assertEquals(EXPECTED_RETURN_LIST, biblioteca.listBooks());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryReturnNonexistentBook(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.returnBook("Not In The List");
                });

        assertEquals("That is not a valid book name",testedException.getMessage());

    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryReturnBookAvailable(){
        IllegalArgumentException testedException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.returnBook("Name Item 1");
                });

        assertEquals("That is not a valid book to return",testedException.getMessage());
    }

}

