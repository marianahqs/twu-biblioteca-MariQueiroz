package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private final List<Book> books;

    public Biblioteca(List<Book> books) {
        this.books = books;
    }

    public List<Book> listBooks() {

        return books.stream().filter(p -> p.isAvailable()).collect(Collectors.toList());
    }
}
