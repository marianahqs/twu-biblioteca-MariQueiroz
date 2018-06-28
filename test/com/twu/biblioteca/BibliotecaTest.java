package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BibliotecaTest {
    @Test
    public void shouldReturnTheListOfBooks() {
        List<Book> books = List.of(new Book("Nome", "Autor", 2018, true),
                new Book("Outro Nome", "Autor", 2017, true));

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals(books, biblioteca.listBooks());
    }

    @Test
    public void shouldReturnOnlyAvailableBook(){
        List<Book> availableBooks = List.of(new Book("Nome", "Autor", 1980, true),new Book("Nome2", "Autor2",2003,true));
        List<Book> unavailableBooks = List.of(new Book("Outro Nome", "Autor", 2017, false), new Book("Nome3","Autor3",1999,false));

        List<Book> books = new ArrayList<>(availableBooks);
        books.addAll(unavailableBooks);

        Biblioteca biblioteca = new Biblioteca(books);

        assertEquals(availableBooks, biblioteca.listBooks());
    }


}

