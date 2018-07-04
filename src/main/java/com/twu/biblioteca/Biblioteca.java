package com.twu.biblioteca;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Biblioteca {

    private final List<Book> books;

    public Biblioteca(List<Book> books) {
        this.books = books;
    }

    public List<Book> listBooks() {
        return books.stream().filter(p -> p.getIsAvailable()).collect(Collectors.toList());
    }

    public boolean checkoutBook(String nameOfBookToCheckout) throws NoSuchElementException, IllegalArgumentException {
        boolean returnFlag = true;

        Book bookToCheckout = getBook(nameOfBookToCheckout);

        if (bookToCheckout.getIsAvailable()){
            bookToCheckout.checkoutBook();
        } else{
            throw new IllegalArgumentException("That book is not available");
        }
        return returnFlag;
    }


    public boolean returnBook(String nameOfBookToReturn) throws NoSuchElementException, IllegalArgumentException {
        boolean returnFlag = true;
        Book bookToReturn = getBook(nameOfBookToReturn);

        if (bookToReturn.getIsAvailable()){
            throw new IllegalArgumentException("That is not a valid book to return");
        }
        bookToReturn.returnBook();
        return returnFlag;
    }


    public Book getBook(String nameOfBookToCheckout) throws NoSuchElementException {
        try {
            return books.stream()
                    .filter(p -> p.getName().equals(nameOfBookToCheckout))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            throw new NoSuchElementException("That is not a valid book name");
        }
    }

}
