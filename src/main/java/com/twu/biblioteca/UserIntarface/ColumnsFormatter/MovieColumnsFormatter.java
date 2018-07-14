package com.twu.biblioteca.UserIntarface.ColumnsFormatter;

import com.twu.biblioteca.BibliotecaComponents.Item;
import com.twu.biblioteca.BibliotecaComponents.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class MovieColumnsFormatter {

    private static List<Movie> movies;

    public MovieColumnsFormatter(List<Movie> movies) {
        this.movies = movies;
    }

    public List<String> formatColumns(){
        int COLUMN_DISTANCE = 5;
        List<String > returnList = new ArrayList<>();

        int nameColumnSize = getColumnSize(COLUMN_DISTANCE, Movie::getName);
        int directorColumnSize = getColumnSize(COLUMN_DISTANCE, Movie::getDirector);
        int ratingColumnSize = getColumnSize(COLUMN_DISTANCE, Movie::getRating);
        int yearColumnSize = getColumnSize(COLUMN_DISTANCE, Movie::getYear);

        for (Movie movie : movies){

            returnList.add(String.format("%-" + nameColumnSize +
                            "s%-" + directorColumnSize+
                            "s%-" + ratingColumnSize+
                            "s%-" + yearColumnSize + "s%s",
                    movie.getName(),
                    movie.getDirector(),
                    movie.getRating(),
                    movie.getYear(),
                    ofNullable(movie.getUserId()).orElse("")));
        }
        return returnList;
    }

    private int getColumnSize(int COLUMN_DISTANCE, Function<Movie, String> mapItem) {
        return getSizeBiggestItem(movies.stream().
                    map(mapItem).
                    collect(Collectors.toList()))
                    +COLUMN_DISTANCE;
    }

    public int getSizeBiggestItem(List<String> listToTest)throws NoSuchElementException {
        try {
            String maxString = listToTest.stream().max((current, other) -> {
                Integer length = current.length();
                return length.compareTo(other.length());
            }).get();

            return maxString.length();
        }catch (NoSuchElementException noItem){
            throw new NoSuchElementException("Movies list is empty");
        }
    }
}
