package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    @Test
    public void shouldReturnTheListOfBooksFromMenu() {
        List<Book> books = List.of(new Book("Name Book 1", "Author Book 1", 2018, true),
                new Book("Name Book 2", "Author Book 2", 2017, true));

        List<Book> actual = BibliotecaApp.menuSelection("1");
        assertEquals(books, actual);
    }
}
