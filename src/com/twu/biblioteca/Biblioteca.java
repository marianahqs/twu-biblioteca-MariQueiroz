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

    public String checkOutBook(String nameBookToCheckOut) {
        String returnString = "";

        try {
            Book bookToCheckOut = books.stream()
                    .filter(p -> p.getName().equals(nameBookToCheckOut))
                    .findFirst()
                    .get();

            if (bookToCheckOut.getIsAvailable()){
                bookToCheckOut.checkOutBook();
                returnString = "Thank you! Enjoy the book";

            } else{
                returnString = "That book is not available";
            }

            return returnString;

        } catch (NoSuchElementException nexc){
            returnString = "That is not a valid book name";

        } finally {

        }
        return returnString;

    }

    public String returnBook(String nameBookToReturn) {
        String returnString = "";
        try{
            Book bookToReturn = books.stream()
                    .filter(p -> p.getName().equals(nameBookToReturn))
                    .findFirst()
                    .get();

            if (!bookToReturn.getIsAvailable()){
                bookToReturn.returnBook();
                returnString = "Thank you for returning the book";

            } else {
                returnString = "That is not a valid book to return";
            }

        } catch (NoSuchElementException nexc){
            returnString = "That is not a valid book name";

        } finally {

        }
        return returnString;
    }
}
