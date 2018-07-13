package com.twu.biblioteca.BibliotecaControl;

import com.twu.biblioteca.Components.Book;
import com.twu.biblioteca.Components.Item;
import com.twu.biblioteca.Components.Movie;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Biblioteca {

    private final List<Book> books;
    private final List<Movie> movies;

    public Biblioteca(List<Book> books, List<Movie> movies) {
        this.books = books;
        this.movies = movies;
    }

    public List<Book> listBooks() {
        return books.stream().filter(p -> p.getIsAvailable()).collect(Collectors.toList());
    }

    public List<Movie> listMovies() {
        return movies.stream().filter(p -> p.getIsAvailable()).collect(Collectors.toList());
    }

    public boolean checkoutBook(String nameOfBookToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        Item bookToCheckout = getItem(nameOfBookToCheckout,books);
        return checkoutItem(loggedUserId, bookToCheckout);
    }

    public boolean returnBook(String nameOfBookToReturn) throws NoSuchElementException, IllegalArgumentException {
        Item bookToReturn = getItem(nameOfBookToReturn,books);
        return returnItem(bookToReturn);
    }

    public boolean checkoutMovie(String nameOfMovieToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        Item movieToCheckout = getItem(nameOfMovieToCheckout,movies);
        return checkoutItem(loggedUserId,movieToCheckout);
    }

    public boolean returnMovie(String nameOfMovieToReturn) throws NoSuchElementException, IllegalArgumentException {
        Item movieToReturn = getItem(nameOfMovieToReturn,movies);
        return returnItem(movieToReturn);
    }

    public Item getItem(String nameOfItem, List<? extends Item> listToSearch) throws NoSuchElementException {
        try {
            return listToSearch.stream()
                    .filter(p -> p.getName().equals(nameOfItem))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            String message = String.format("That is not a valid %s name", listToSearch.get(0).getClass().getSimpleName().toLowerCase());
            throw new NoSuchElementException(message);
        }
    }

    private boolean returnItem(Item itemToReturn) {
        if (itemToReturn.getIsAvailable()){
            String message = String.format("That is not a valid %s to return", itemToReturn.getClass().getSimpleName().toLowerCase());
            throw new IllegalArgumentException(message);
        }
        itemToReturn.makeItAvailable();
        return true;
    }

    private boolean checkoutItem(String loggedUserId, Item itemToCheckout) {
        if (!itemToCheckout.getIsAvailable()){
            String message = String.format("That %s is not available", itemToCheckout.getClass().getSimpleName().toLowerCase());
            throw new IllegalArgumentException(message);
        }
        itemToCheckout.makeItUnavailable(loggedUserId);
        return true;
    }
}