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

    private List<Item> listItems() {
        return books.stream().filter(p -> p.getIsAvailable()).collect(Collectors.toList());
    }

    public boolean checkoutBook(String nameOfBookToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        Book bookToCheckout = getBook(nameOfBookToCheckout);

        if (!bookToCheckout.getIsAvailable()){
            throw new IllegalArgumentException("That book is not available");
        }
        bookToCheckout.checkoutBook();
        bookToCheckout.setUserId(loggedUserId);
        return true;
    }

    public boolean returnBook(String nameOfBookToReturn) throws NoSuchElementException, IllegalArgumentException {
        Book bookToReturn = getBook(nameOfBookToReturn);

        if (bookToReturn.getIsAvailable()){
            throw new IllegalArgumentException("That is not a valid book to return");
        }
        bookToReturn.returnBook();
        bookToReturn.setUserId(null);
        return true;
    }


    public Book getBook(String nameOfBook) throws NoSuchElementException {
        try {
            return books.stream()
                    .filter(p -> p.getName().equals(nameOfBook))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            throw new NoSuchElementException("That is not a valid book name");
        }
    }

    public boolean checkoutMovie(String nameOfMovieToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        Movie movieToCheckout = getMovie(nameOfMovieToCheckout);

        if (!movieToCheckout.getIsAvailable()){
            throw new IllegalArgumentException("That movie is not available");
        }
        movieToCheckout.checkoutMovie();
        movieToCheckout.setUserId(loggedUserId);
        return true;
    }


    public boolean returnMovie(String nameOfMovieToReturn) throws NoSuchElementException, IllegalArgumentException {
        Movie movieToReturn = getMovie(nameOfMovieToReturn);
        if (movieToReturn.getIsAvailable()){
            throw new IllegalArgumentException("That is not a valid movie to return");
        }
        movieToReturn.returnMovie();
        movieToReturn.setUserId(null);
        return true;
    }


    public Movie getMovie(String nameOfMovie) throws NoSuchElementException {
        try {
            return movies.stream()
                    .filter(p -> p.getName().equals(nameOfMovie))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            throw new NoSuchElementException("That is not a valid movie name");
        }
    }
}
