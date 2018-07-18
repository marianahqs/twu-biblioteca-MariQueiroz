package com.twu.biblioteca;

import com.twu.biblioteca.UserIntarface.ColumnsFormatter.ItemsColumnsFormatter;
import com.twu.biblioteca.BibliotecaComponents.Book;
import com.twu.biblioteca.UserIntarface.ColumnsFormatter.BooksColumnsFormatter;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BooksColumnsFormatterTest {
    List<Book> BOOKS = Arrays.asList(new Book("small", "author small", "1988", true, null),
            new Book("big","author", "1987", true, null),
            new Book("really big name", "author", "1987", true, null));
    List<String> listFields = Arrays.asList("getName","getAuthor","getYear","getUserId");

  //  @Test
    public void shouldFindTheSizeOfTheBiggestItem() {
        ItemsColumnsFormatter<Book> formatter = new BooksColumnsFormatter(BOOKS);
        int SIZE_EXPECTED = 15;
        List<String> LIST_TO_TEST = Arrays.asList("small","big" ,"really big name");
    }

    @Test
    public void shouldFormatColumnsForBooksList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ItemsColumnsFormatter<Book> formatter = new BooksColumnsFormatter(BOOKS);
        List<String> RETURN_LIST_EXPECTED = Arrays.asList("NAME                AUTHOR           YEAR     USER ID     ",
                "small               author small     1988                 ",
                "big                 author           1987                 ",
                "really big name     author           1987                 ");

        assertEquals(RETURN_LIST_EXPECTED,formatter.createTable());
    }
}