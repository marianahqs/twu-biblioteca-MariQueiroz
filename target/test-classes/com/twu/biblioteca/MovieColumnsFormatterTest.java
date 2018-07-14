package com.twu.biblioteca;

import com.twu.biblioteca.UserIntarface.ColumnsFormatter.MovieColumnsFormatter;
import com.twu.biblioteca.BibliotecaComponents.Movie;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MovieColumnsFormatterTest {
    List<Movie> MOVIES = List.of(new Movie("Name 1", "Director Movie 1", "1980","8.1" ,true),
            new Movie("Name Movie", "Director 2","2003","none",false),
            new Movie("Name 3", "Director Movie", "1970","9", false),
            new Movie("Name","Director","1999","none",true));

    @Test
    public void shouldFindTheSizeOfTheBiggestItem() {
        MovieColumnsFormatter formatter = new MovieColumnsFormatter(MOVIES);
        int SIZE_EXPECTED = 15;
        List<String> LIST_TO_TEST = List.of("small","big" ,"really big name");

        assertEquals(formatter.getSizeBiggestItem(LIST_TO_TEST), SIZE_EXPECTED);
    }

    @Test
    public void shouldFormatColumnsForMoviesList() {
        MovieColumnsFormatter formatter = new MovieColumnsFormatter(MOVIES);
        List<String> RETURN_LIST_EXPECTED = List.of("Name 1         Director Movie 1     8.1      1980     ",
                "Name Movie     Director 2           none     2003     ",
                "Name 3         Director Movie       9        1970     ",
                "Name           Director             none     1999     ");

        assertEquals(RETURN_LIST_EXPECTED,formatter.formatColumns());
    }
}