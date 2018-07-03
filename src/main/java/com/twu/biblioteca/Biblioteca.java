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

    public boolean checkoutBook(String nameOfBookToCheckout) throws NoSuchElementException {
        boolean returnFlag;

        try {
            Book bookToCheckout = books.stream()
                    .filter(p -> p.getName().equals(nameOfBookToCheckout))
                    .findFirst()
                    .get();

            if (bookToCheckout.getIsAvailable()){
                bookToCheckout.checkoutBook();
                returnFlag = true;

            } else{
                throw new IllegalArgumentException("That book is not available");
            }

            return returnFlag;

        } catch (NoSuchElementException nexc){
            nexc.getMessage();
            throw new NoSuchElementException("That is not a valid book name");
        }
    }

    public boolean returnBook(String nameOfBookToReturn) throws NoSuchElementException, IllegalArgumentException {
        boolean returnFlag;
        try{
            Book bookToReturn = books.stream()
                    .filter(p -> p.getName().equals(nameOfBookToReturn))
                    .findFirst()
                    .get();

            if (!bookToReturn.getIsAvailable()){
                bookToReturn.returnBook();
                returnFlag = true;

            } else {
                throw new IllegalArgumentException("That is not a valid book to return");
            }

        } catch (NoSuchElementException nexc){
            nexc.getMessage();
            throw new NoSuchElementException("That is not a valid book name");
        }
        return returnFlag;
    }
}
