package com.twu.biblioteca;

import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {
    private final List<Book> books;

    public Biblioteca(List<Book> books) {
        this.books = books;
    }

    public List<Book> listBooks() {
        return books.stream().filter(p -> p.getIsAvailable()).collect(Collectors.toList());
    }

    public void checkOutBook(String nameBookToCheckOut) {
        Book bookToCheckOut = books.stream()
                .filter(p -> p.getName().equals(nameBookToCheckOut))
                .findFirst()
                .get();

        bookToCheckOut.checkOutBook();
    }

    public void returnBook(String nameBookToReturn) {
        Book bookToCheckOut = books.stream()
                .filter(p -> p.getName().equals(nameBookToReturn))
                .findFirst()
                .get();

        bookToCheckOut.returnBook();
    }
}
