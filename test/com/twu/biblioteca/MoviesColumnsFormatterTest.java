package com.twu.biblioteca;

import com.twu.biblioteca.UserIntarface.ColumnsFormatter.MoviesColumnsFormatter;
import com.twu.biblioteca.BibliotecaComponents.Movie;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoviesColumnsFormatterTest {
    List<Movie> MOVIES = List.of(new Movie("Name 1", "Director Movie 1", "1980","8.1" ,true, null),
            new Movie("Name Movie", "Director 2","2003","none",false, null),
            new Movie("Name 3", "Director Movie", "1970","9", false, null),
            new Movie("Name","Director","1999","none",true, null));

    @Test
    public void shouldFindTheSizeOfTheBiggestItem() {
        MoviesColumnsFormatter formatter = new MoviesColumnsFormatter(MOVIES);
        int SIZE_EXPECTED = 15;
        List<String> LIST_TO_TEST = List.of("small","big" ,"really big name");

        assertEquals(formatter.getSizeBiggestItem(LIST_TO_TEST), SIZE_EXPECTED);
    }

    @Test
    public void shouldFormatColumnsForMoviesList() {
        MoviesColumnsFormatter formatter = new MoviesColumnsFormatter(MOVIES);
        List<String> RETURN_LIST_EXPECTED = List.of("NAME           DIRECTOR             RATING   YEAR     USER ID",
                "Name 1         Director Movie 1     8.1      1980     ",
                "Name Movie     Director 2           none     2003     ",
                "Name 3         Director Movie       9        1970     ",
                "Name           Director             none     1999     ");

        assertEquals(RETURN_LIST_EXPECTED,formatter.formatColumns());
    }
}