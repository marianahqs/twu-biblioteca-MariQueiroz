package com.twu.biblioteca.ColumnsFormatter;

import com.twu.biblioteca.Components.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MovieColumnsFormatter {

    private static List<Movie> movies;

    public MovieColumnsFormatter(List<Movie> movies) {
        this.movies = movies;
    }

    public List<String> formatColumns(){
        int COLUMN_DISTANCE = 5;
        List<String > returnList = new ArrayList<>();
        int nameColumnSize = getSizeBiggestItem(movies.stream().
                map(Movie::getName).
                collect(Collectors.toList()))
                +COLUMN_DISTANCE;

        int directorColumnSize = getSizeBiggestItem(movies.stream().
                map(Movie::getDirector).
                collect(Collectors.toList()))
                +COLUMN_DISTANCE;

        int ratingColumnSize = getSizeBiggestItem(movies.stream().
                map(Movie::getRating).
                collect(Collectors.toList()))
                +COLUMN_DISTANCE;

        for (int line = 0; line < movies.size(); line++){

            returnList.add(String.format("%-" + nameColumnSize + "s%-" + directorColumnSize+"s%-" + ratingColumnSize + "s%s",
                    movies.get(line).getName(),
                    movies.get(line).getDirector(),
                    movies.get(line).getRating(),
                    movies.get(line).getYear()));
        }
        return returnList;
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
