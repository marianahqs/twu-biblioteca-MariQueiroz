package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class BibliotecaTest {
    List<Item> BOOKS = List.of(Item.createBook("Name Book 1", "Author Book 1", 1980, true),
            Item.createBook("Name Book 2", "Author Book 2",2003,true),
            Item.createBook("Name Book 3", "Author Book 3", 2017, false),
            Item.createBook("Name Book 4","Author Book 4",1999,false));

    List<Item> MOVIES = List.of(Item.createMovie("Name Movie 1", "Director Movie 1", 1980,"8.1" ,true),
            Item.createMovie("Name Movie 2", "Director Movie 2",2003,"none",false),
            Item.createMovie("Name Movie 3", "Director Movie 3", 2017,"9", false),
            Item.createMovie("Name Movie 4","Director Movie 4",1999,"none",true));

    List < Item> ITEMS = Stream.concat(BOOKS.stream(), MOVIES.stream()).collect(Collectors.toList());

    Biblioteca biblioteca = new Biblioteca(ITEMS);

    //List Books
    @Test
    public void shouldReturnOnlyAvailableBooks(){
        List<Item> EXPECTED_RETURN_LIST = List.of(Item.createBook("Name Book 1", "Author Book 1", 1980, true),
                Item.createBook("Name Book 2", "Author Book 2",2003,true));

        assertEquals(EXPECTED_RETURN_LIST, biblioteca.listBooks());
    }


    //List Movies
    @Test
    public void shouldReturnOnlyAvailableMovies(){
        List<Item> EXPECTED_RETURN_LIST = List.of(Item.createMovie("Name Movie 1", "Director Movie 1", 1980,"8.1" ,true),
                Item.createMovie("Name Movie 4","Director Movie 4",1999,"none",true));

        assertEquals(EXPECTED_RETURN_LIST, biblioteca.listMovies());
    }


    //Checkout Items
    @Test
    public void shouldReturnTrueWhenCheckoutSucceed(){
        assertTrue(biblioteca.checkoutItem("Name Book 1"));
        assertTrue(biblioteca.checkoutItem("Name Movie 4"));
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryCheckoutNonexistentItem(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.checkoutItem("Not In The List");
                });

        assertEquals("That is not a valid item name",testedException.getMessage());

    }

    @Test
    public void shouldReturnListWithoutCheckedOutItem(){
        List<Item> EXPECTED_BOOK_RETURN_LIST = List.of(Item.createBook("Name Book 1", "Author Book 1", 1980, true));

        biblioteca.checkoutItem("Name Book 2");
        assertEquals(EXPECTED_BOOK_RETURN_LIST, biblioteca.listBooks());

        List<Item> EXPECTED_MOVIE_RETURN_LIST = List.of( Item.createMovie("Name Movie 4","Director Movie 4",1999,"none",true));

        biblioteca.checkoutItem("Name Movie 1");
        assertEquals(EXPECTED_MOVIE_RETURN_LIST, biblioteca.listMovies());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryCheckoutUnavailableItem(){
        IllegalArgumentException testedBookException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.checkoutItem("Name Book 4");
                });

        assertEquals("That item is not available",testedBookException.getMessage());


        IllegalArgumentException testedMovieException = assertThrows(IllegalArgumentException.class,
        () -> {
            biblioteca.checkoutItem("Name Movie 2");
        });

        assertEquals("That item is not available",testedMovieException.getMessage());
    }


    //Return Items
    @Test
    public void shouldReturnTrueWhenReturnSucceed(){
        assertTrue(biblioteca.returnItem("Name Book 3"));
        assertTrue(biblioteca.returnItem("Name Movie 2"));
    }

    @Test
    public void shouldReturnListWithReturnedItem(){
        List<Item> EXPECTED_BOOK_RETURN_LIST = List.of(Item.createBook("Name Book 1", "Author Book 1", 1980, true),
               Item.createBook("Name Book 2", "Author Book 2",2003,true),
                Item.createBook("Name Book 4", "Author Book 4", 1999, true));

        biblioteca.returnItem("Name Book 4");
        assertEquals(EXPECTED_BOOK_RETURN_LIST, biblioteca.listBooks());

        List<Item> EXPECTED_MOVIE_RETURN_LIST = List.of(Item.createMovie("Name Movie 1", "Director Movie 1", 1980,"8.1" ,true),
                Item.createMovie("Name Movie 3", "Director Movie 3", 2017,"9", true),
                Item.createMovie("Name Movie 4","Director Movie 4",1999,"none",true));

        biblioteca.returnItem("Name Movie 3");
        assertEquals(EXPECTED_MOVIE_RETURN_LIST, biblioteca.listMovies());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTryReturnNonexistentItem(){
        NoSuchElementException testedException = assertThrows(NoSuchElementException.class,
                () -> {
                    biblioteca.returnItem("Not In The List");
                });

        assertEquals("That is not a valid item name",testedException.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTryReturnItemAvailable(){
        IllegalArgumentException testedBookException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.returnItem("Name Book 1");
                });

        assertEquals("That is not a valid item to return",testedBookException.getMessage());


        IllegalArgumentException testedMovieException = assertThrows(IllegalArgumentException.class,
                () -> {
                    biblioteca.returnItem("Name Movie 1");
                });

        assertEquals("That is not a valid item to return",testedMovieException.getMessage());
    }

}

