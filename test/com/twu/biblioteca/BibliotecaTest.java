package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaControl.Biblioteca;
import com.twu.biblioteca.BibliotecaComponents.Book;
import com.twu.biblioteca.BibliotecaComponents.Movie;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BibliotecaTest {
    List<Book> BOOKS = List.of(new Book("Name Book 1", "Author Book 1", "1980", true),
            new Book("Name Book 2", "Author Book 2","2003",true),
            new Book("Name Book 3", "Author Book 3", "2017", false),
            new Book("Name Book 4","Author Book 4","1999",false));

    List<Movie> MOVIES = List.of(new Movie("Name Movie 1", "Director Movie 1", "1980","8.1" ,true),
            new Movie("Name Movie 2", "Director Movie 2","2003","none",false),
            new Movie("Name Movie 3", "Director Movie 3", "2017","9", false),
            new Movie("Name Movie 4","Director Movie 4","1999","none",true));

    Biblioteca biblioteca = new Biblioteca(BOOKS,MOVIES);

    //List Books
    @Test
    public void shouldReturnOnlyAvailableBooks(){
        List<Book> EXPECTED_RETURN_LIST = List.of(new Book("Name Book 1", "Author Book 1", "1980", true),
                new Book("Name Book 2", "Author Book 2","2003",true));

        assertEquals(EXPECTED_RETURN_LIST, biblioteca.listAvailableBooks());
    }

    @Test
    public void shouldReturnAllBooks(){
        assertEquals(BOOKS, biblioteca.listAllBooks());
    }


    //List Movies
    @Test
    public void shouldReturnOnlyAvailableMovies(){
        List<Movie> EXPECTED_RETURN_LIST = List.of(new Movie("Name Movie 1", "Director Movie 1", "1980","8.1" ,true),
                new Movie("Name Movie 4","Director Movie 4","1999","none",true));

        assertEquals(EXPECTED_RETURN_LIST, biblioteca.listAvailableMovies());
    }

    @Test
    public void shouldReturnAllMovies(){
        assertEquals(MOVIES, biblioteca.listAllMovies());
    }

    //Checkout Items
    @Test
    public void shouldReturnTrueWhenBookCheckoutSucceed() {
        assertTrue(biblioteca.checkoutBook("Name Book 1", "222-33333"));
    }

    @Test
    public void shouldReturnTrueWhenMovieCheckoutSucceed(){
        assertTrue(biblioteca.checkoutMovie("Name Movie 4","2222-3344"));
    }

    @Test
    public void shouldUpdateUserIdForCheckedOutBook() {
        String USER_ID_TEST = "222-3344";

        assertEquals(null, biblioteca.getItem("Name Book 1",BOOKS).getUserId());
        biblioteca.checkoutBook("Name Book 1", USER_ID_TEST);
        assertEquals(USER_ID_TEST, biblioteca.getItem("Name Book 1",BOOKS).getUserId());
    }

    @Test
    public void shouldUpdateUserIdForCheckedOutMovie(){
        String USER_ID_TEST = "222-3344";

        assertEquals(null,biblioteca.getItem("Name Movie 4",MOVIES).getUserId());
        biblioteca.checkoutMovie("Name Movie 4",USER_ID_TEST);
        assertEquals(USER_ID_TEST,biblioteca.getItem("Name Movie 4",MOVIES).getUserId());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryCheckoutNonexistentBook(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.checkoutBook("Not In The List","xxx-xxxx");
                });

        assertEquals("That is not a valid book name",testedException.getMessage());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryCheckoutNonexistentMovie(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.checkoutMovie("Not In The List","xxx-xxxx");
                });

        assertEquals("That is not a valid movie name",testedException.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryCheckoutUnavailableBook() {
        IllegalArgumentException testedBookException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.checkoutBook("Name Book 4", "xxx-xxxx");
                });

        assertEquals("That book is not available", testedBookException.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryCheckoutUnavailableMovie(){
        IllegalArgumentException testedMovieException = assertThrows(IllegalArgumentException.class,
        () -> {
            biblioteca.checkoutMovie("Name Movie 2","xxx-xxxx");
        });

        assertEquals("That movie is not available",testedMovieException.getMessage());
    }


    //Return Items
    @Test
    public void shouldReturnTrueWhenReturnBookSucceed() {
        assertTrue(biblioteca.returnBook("Name Book 3"));
    }

    @Test
    public void shouldReturnTrueWhenReturnMovieSucceed(){
        assertTrue(biblioteca.returnMovie("Name Movie 2"));
    }

    @Test
    public void shouldClearUserIdForReturnedBook() {
        String USER_ID_TEST = "222-3344";

        biblioteca.checkoutBook("Name Book 1", USER_ID_TEST);
        assertEquals(USER_ID_TEST, biblioteca.getItem("Name Book 1",BOOKS).getUserId());
        biblioteca.returnBook("Name Book 1");
        assertEquals(null, biblioteca.getItem("Name Book 1",BOOKS).getUserId());
    }

    @Test
    public void shouldClearUserIdForReturnedMovie(){
        String USER_ID_TEST = "222-3344";

        biblioteca.checkoutMovie("Name Movie 4",USER_ID_TEST);
        assertEquals(USER_ID_TEST,biblioteca.getItem("Name Movie 4",MOVIES).getUserId());
        biblioteca.returnMovie("Name Movie 4");
        assertEquals(null,biblioteca.getItem("Name Movie 4",MOVIES).getUserId());
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
    public void shouldThrowNoSuchElementExceptionWhenTryReturnNonexistentMovie(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.returnMovie("Not In The List");
                });

        assertEquals("That is not a valid movie name",testedException.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryReturnAvailableBook() {
        IllegalArgumentException testedBookException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.returnBook("Name Book 1");
                });

        assertEquals("That is not a valid book to return", testedBookException.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryReturnAvailableMovie(){
        IllegalArgumentException testedMovieException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.returnMovie("Name Movie 1");
                });

        assertEquals("That is not a valid movie to return",testedMovieException.getMessage());
    }
}

