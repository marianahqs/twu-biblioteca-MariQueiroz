package com.twu.biblioteca.BibliotecaControl;

import com.twu.biblioteca.BibliotecaComponents.Book;
import com.twu.biblioteca.BibliotecaComponents.Item;
import com.twu.biblioteca.BibliotecaComponents.Movie;

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

    // Books

    public List<Book> listAvailableBooks() {
        return books.stream().filter(p -> p.isAvailable()).collect(Collectors.toList());
    }

    public List<Book> listAllBooks() {
        return books;
    }

    public boolean checkoutBook(String nameOfBookToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        return checkoutItem(loggedUserId, findItem(nameOfBookToCheckout,books));
    }

    public boolean returnBook(String nameOfBookToReturn) throws NoSuchElementException, IllegalArgumentException {
        return returnItem(findItem(nameOfBookToReturn,books));
    }


    //Movies

    public List<Movie> listAvailableMovies() {
        return movies.stream().filter(p -> p.isAvailable()).collect(Collectors.toList());
    }

    public List<Movie> listAllMovies() {
        return movies;
    }

    public boolean checkoutMovie(String nameOfMovieToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        return checkoutItem(loggedUserId, findItem(nameOfMovieToCheckout,movies));
    }

    public boolean returnMovie(String nameOfMovieToReturn) throws NoSuchElementException, IllegalArgumentException {
        return returnItem(findItem(nameOfMovieToReturn,movies));
    }


    // Generic

    public Item findItem(String nameOfItem, List<? extends Item> listToSearch) throws NoSuchElementException {
        try {
            return listToSearch.stream()
                    .filter(p -> p.getName().equals(nameOfItem))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            String message = String.format("That is not a valid %s name",
                    listToSearch.get(0).getClass().getSimpleName().toLowerCase());
            throw new NoSuchElementException(message);
        }
    }

    private boolean returnItem(Item itemToReturn) {
        if (itemToReturn.isAvailable()){
            String message = String.format("That is not a valid %s to return", itemToReturn.getClass().getSimpleName().toLowerCase());
            throw new IllegalArgumentException(message);
        }
        itemToReturn.makeItAvailable();
        return true;
    }

    private boolean checkoutItem(String loggedUserId, Item itemToCheckout) {
        if (!itemToCheckout.isAvailable()){
            String message = String.format("That %s is not available", itemToCheckout.getClass().getSimpleName().toLowerCase());
            throw new IllegalArgumentException(message);
        }
        itemToCheckout.makeItUnavailable(loggedUserId);
        return true;
    }
}
