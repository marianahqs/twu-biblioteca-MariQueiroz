package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaComponents.Item;
import com.twu.biblioteca.BibliotecaControl.BooksControl;
import com.twu.biblioteca.BibliotecaComponents.Book;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BooksControlTest {
    List<Book> BOOKS = List.of(new Book("Name Book 1", "Author Book 1", "1980", true, null),
            new Book("Name Book 2", "Author Book 2","2003",true, null),
            new Book("Name Book 3", "Author Book 3", "2017", false, null),
            new Book("Name Book 4","Author Book 4","1999",false, null));

    BooksControl booksControl = new BooksControl(BOOKS);
    String USER_ID_TEST = "222-3344";

    private Item findItem(String nameOfItem, List<? extends Item> listToSearch) throws NoSuchElementException {
        try {
            return listToSearch.stream()
                    .filter(p -> p.getName().equals(nameOfItem))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            String message = String.format("That is not a valid %s name",
                    listToSearch.get(0).getClass().getSimpleName().toLowerCase());
            throw new NoSuchElementException(message);
        }
    }


//List
    @Test
    public void shouldReturnOnlyAvailableBooks(){
        List<Book> EXPECTED_RETURN_LIST = List.of(new Book("Name Book 1", "Author Book 1", "1980", true, null),
                new Book("Name Book 2", "Author Book 2","2003",true, null));

        assertEquals(EXPECTED_RETURN_LIST, booksControl.listAvailableBooks());
    }

    @Test
    public void shouldReturnAllBooks(){
        assertEquals(BOOKS, booksControl.listAllBooks());
    }


//Checkout
    @Test
    public void shouldReturnTrueWhenBookCheckoutSucceed() {
        assertTrue(booksControl.checkoutBook("Name Book 1", "222-33333"));
    }

    @Test
    public void shouldUpdateUserIdForCheckedOutBook() {
        assertEquals(null, findItem("Name Book 1",BOOKS).getUserId());
        booksControl.checkoutBook("Name Book 1", USER_ID_TEST);
        assertEquals(USER_ID_TEST, findItem("Name Book 1",BOOKS).getUserId());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryCheckoutNonexistentBook(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    booksControl.checkoutBook("Not In The List","xxx-xxxx");
                });

        assertEquals("That is not a valid book name",testedException.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryCheckoutUnavailableBook() {
        IllegalArgumentException testedBookException = assertThrows(IllegalArgumentException.class,
                () -> {
                    booksControl.checkoutBook("Name Book 4", "xxx-xxxx");
                });

        assertEquals("That book is not available", testedBookException.getMessage());
    }


 //Return
    @Test
    public void shouldReturnTrueWhenReturnBookSucceed() {
        assertTrue(booksControl.returnBook("Name Book 3"));
    }

    @Test
    public void shouldClearUserIdForReturnedBook() {
        booksControl.checkoutBook("Name Book 1", USER_ID_TEST);
        assertEquals(USER_ID_TEST, findItem("Name Book 1",BOOKS).getUserId());
        booksControl.returnBook("Name Book 1");
        assertEquals(null, findItem("Name Book 1",BOOKS).getUserId());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryReturnNonexistentBook(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    booksControl.returnBook("Not In The List");
                });

        assertEquals("That is not a valid book name",testedException.getMessage());
    }

     @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryReturnAvailableBook() {
        IllegalArgumentException testedBookException = assertThrows(IllegalArgumentException.class,
                () -> {
                    booksControl.returnBook("Name Book 1");
                });

        assertEquals("That is not a valid book to return", testedBookException.getMessage());
    }
}

