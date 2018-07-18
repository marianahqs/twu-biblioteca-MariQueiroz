package com.twu.biblioteca.BibliotecaControl;

import com.twu.biblioteca.BibliotecaComponents.Book;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BooksControl extends ItemsControl {

    private final List<Book> books;

    public BooksControl(List<Book> books) {
        this.books = books;
    }

    public List<Book> listAvailableBooks() {
        return books.stream().filter(p -> p.isAvailable()).collect(Collectors.toList());
    }

    public List<Book> listAllBooks() {
        return books;
    }

    public boolean checkoutBook(String nameOfBookToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        return checkoutItem(loggedUserId, getItem(nameOfBookToCheckout, books));
    }

    public boolean returnBook(String nameOfBookToReturn) throws NoSuchElementException, IllegalArgumentException {
        return returnItem(getItem(nameOfBookToReturn, books));
    }
}