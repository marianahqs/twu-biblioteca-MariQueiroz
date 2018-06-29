package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

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
    public void shouldReturnListWithoutCheckedOutBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        List<Book> expectedReturnList = List.of(new Book("Name Book 1", "Author Book 1", 1980, true));

        Biblioteca biblioteca = new Biblioteca(books);
        biblioteca.checkOutBook("Name Book 2");

        assertEquals(expectedReturnList, biblioteca.listBooks());
    }

    @Test
    public void shouldReturnSuccessMessageWhenCheckOutSucceed(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals("Thank you! Enjoy the book",  biblioteca.checkOutBook("Name Book 1"));
    }

    @Test
    public void shouldReturnNotValidBookNameMessageWhenTryCheckOutNonexistentBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals("That is not a valid book name", biblioteca.checkOutBook("Not In The List"));
    }

    @Test
    public void shouldReturnNotAvailableBookMessageNameWhenTryCheckOutUnavailableBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals("That book is not available", biblioteca.checkOutBook("Name Book 3"));
    }


    //Return Books
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
    public void shouldReturnSuccessMessageWhenReturnSucceed(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals("Thank you for returning the book",  biblioteca.returnBook("Name Book 4"));
    }

    @Test
    public void shouldReturnNotValidBookNameMessageWhenTryReturnNonexistentBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals("That is not a valid book name", biblioteca.returnBook("Not In The List"));
    }

    @Test
    public void shouldReturnNotValidBookMessageWhenTryReturnAvailableBook(){
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 1980, true),
                new Book("Name Book 2", "Author Book 2",2003,true),
                new Book("Name Book 3", "Author Book 3", 2017, false),
                new Book("Name Book 4","Author Book 4",1999,false));

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals("That is not a valid book to return", biblioteca.returnBook("Name Book 1"));
    }

}

