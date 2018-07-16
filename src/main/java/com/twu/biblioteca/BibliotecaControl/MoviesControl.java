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

    public List<String > getListFields(){
        return movies.get(0).getFieldsList(); //TODO Gambiarra: what if list is empty?
    }

    public boolean checkoutMovie(String nameOfMovieToCheckout, String loggedUserId) throws NoSuchElementException, IllegalArgumentException {
        return checkoutItem(loggedUserId, findItem(nameOfMovieToCheckout,movies));
    }

    public boolean returnMovie(String nameOfMovieToReturn) throws NoSuchElementException, IllegalArgumentException {
        return returnItem(findItem(nameOfMovieToReturn,movies));
    }

}
