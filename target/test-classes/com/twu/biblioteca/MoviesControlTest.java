package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaComponents.Movie;
import com.twu.biblioteca.BibliotecaControl.MoviesControl;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MoviesControlTest {
    List<Movie> MOVIES = List.of(new Movie("Name Movie 1", "Director Movie 1", "1980","8.1" ,true, null),
            new Movie("Name Movie 2", "Director Movie 2","2003","none",false, null),
            new Movie("Name Movie 3", "Director Movie 3", "2017","9", false, null),
            new Movie("Name Movie 4","Director Movie 4","1999","none",true, null));

    MoviesControl moviesControl = new MoviesControl(MOVIES);
    String USER_ID_TEST = "222-3344";


// List
    @Test
    public void shouldReturnOnlyAvailableMovies(){
        List<Movie> EXPECTED_RETURN_LIST = List.of(new Movie("Name Movie 1", "Director Movie 1", "1980","8.1" ,true, null),
                new Movie("Name Movie 4","Director Movie 4","1999","none",true, null));

        assertEquals(EXPECTED_RETURN_LIST, moviesControl.listAvailableMovies());
    }

    @Test
    public void shouldReturnAllMovies(){
        assertEquals(MOVIES, moviesControl.listAllMovies());
    }


// Checkout
    @Test
    public void shouldReturnTrueWhenMovieCheckoutSucceed(){
        assertTrue(moviesControl.checkoutMovie("Name Movie 4","2222-3344"));
    }

    @Test
    public void shouldUpdateUserIdForCheckedOutMovie(){
        assertEquals(null, MethodsForTests.findItem("Name Movie 4", MOVIES).getUserId());
        moviesControl.checkoutMovie("Name Movie 4",USER_ID_TEST);
        assertEquals(USER_ID_TEST, MethodsForTests.findItem("Name Movie 4", MOVIES).getUserId());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryCheckoutNonexistentMovie(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    moviesControl.checkoutMovie("Not In The List","xxx-xxxx");
                });

        assertEquals("That is not a valid movie name",testedException.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryCheckoutUnavailableMovie(){
        IllegalArgumentException testedMovieException = assertThrows(IllegalArgumentException.class,
        () -> {
            moviesControl.checkoutMovie("Name Movie 2","xxx-xxxx");
        });

        assertEquals("That movie is not available",testedMovieException.getMessage());
    }


//Return
    @Test
    public void shouldReturnTrueWhenReturnMovieSucceed(){
        assertTrue(moviesControl.returnMovie("Name Movie 2"));
    }

    @Test
    public void shouldClearUserIdForReturnedMovie(){
        moviesControl.checkoutMovie("Name Movie 4",USER_ID_TEST);
        assertEquals(USER_ID_TEST, MethodsForTests.findItem("Name Movie 4", MOVIES).getUserId());
        moviesControl.returnMovie("Name Movie 4");
        assertEquals(null, MethodsForTests.findItem("Name Movie 4", MOVIES).getUserId());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryReturnNonexistentMovie(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    moviesControl.returnMovie("Not In The List");
                });

        assertEquals("That is not a valid movie name",testedException.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryReturnAvailableMovie(){
        IllegalArgumentException testedMovieException = assertThrows(IllegalArgumentException.class,
                () -> {
                    moviesControl.returnMovie("Name Movie 1");
                });

        assertEquals("That is not a valid movie to return",testedMovieException.getMessage());
    }
}

