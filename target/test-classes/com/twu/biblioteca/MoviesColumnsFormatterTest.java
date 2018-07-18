package com.twu.biblioteca;

import com.twu.biblioteca.UserIntarface.ColumnsFormatter.ItemsColumnsFormatter;
import com.twu.biblioteca.BibliotecaComponents.Movie;
import com.twu.biblioteca.UserIntarface.ColumnsFormatter.MoviesColumnsFormatter;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MoviesColumnsFormatterTest {
    List<Movie> MOVIES = Arrays.asList(new Movie("Name 1", "Director Movie 1", "1980","8.1" ,true, null),
            new Movie("Name Movie", "Director 2","2003","none",false, null),
            new Movie("Name 3", "Director Movie", "1970","9", false, null),
            new Movie("Name","Director","1999","none",true, null));
    List<String> listFields = Arrays.asList("getName","getDirector","getRating","getYear","getUserId");

 //   @Test
    public void shouldFindTheSizeOfTheBiggestItem() {
        ItemsColumnsFormatter<Movie> formatter = new MoviesColumnsFormatter(MOVIES);
        int SIZE_EXPECTED = 15;
        List<String> LIST_TO_TEST = Arrays.asList("small","big" ,"really big name");
    }

    @Test
    public void shouldFormatColumnsForMoviesList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ItemsColumnsFormatter<Movie> formatter = new MoviesColumnsFormatter(MOVIES);
        List<String> RETURN_LIST_EXPECTED = Arrays.asList("NAME           DIRECTOR             RATING     YEAR     USER ID     ",
                "Name 1         Director Movie 1     8.1        1980                 ",
                "Name Movie     Director 2           none       2003                 ",
                "Name 3         Director Movie       9          1970                 ",
                "Name           Director             none       1999                 ");

        assertEquals(RETURN_LIST_EXPECTED,formatter.createTable());
    }
}