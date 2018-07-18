package com.twu.biblioteca.BibliotecaControl;

import com.twu.biblioteca.BibliotecaComponents.Movie;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MoviesControl extends ItemsControl {

    private final List<Movie> movies;

    public MoviesControl(List<Movie> movies) {
        this.movies = movies;
    }

    public List<Movie> listAvailableMovies() {
        return movies.stream().filter(p -> p.isAvailable()).collect(Collectors.toList());
    }

    public List<Movie> listAllMovies() {
        return movies;
    }

    public boolean checkoutMovie(String nameOfMovieToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        return checkoutItem(loggedUserId, getItem(nameOfMovieToCheckout,movies));
    }

    public boolean returnMovie(String nameOfMovieToReturn) throws NoSuchElementException, IllegalArgumentException {
        return returnItem(getItem(nameOfMovieToReturn,movies));
    }

}
