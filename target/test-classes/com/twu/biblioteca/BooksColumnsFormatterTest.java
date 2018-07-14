package com.twu.biblioteca;

import com.twu.biblioteca.UserIntarface.ColumnsFormatter.BooksColumnsFormatter;
import com.twu.biblioteca.BibliotecaComponents.Book;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BooksColumnsFormatterTest {
    List<Book> BOOKS = List.of(new Book("small", "author small", "1988", true, null),
            new Book("big","author", "1987", true, null),
            new Book("really big name", "author", "1987", true, null));

    @Test
    public void shouldFindTheSizeOfTheBiggestItem() {
        BooksColumnsFormatter formatter = new BooksColumnsFormatter(BOOKS);
        int SIZE_EXPECTED = 15;
        List<String> LIST_TO_TEST = List.of("small","big" ,"really big name");

        assertEquals(formatter.getSizeBiggestItem(LIST_TO_TEST), SIZE_EXPECTED);
    }

    @Test
    public void shouldFormatColumnsForBooksList() {
        BooksColumnsFormatter formatter = new BooksColumnsFormatter(BOOKS);
        List<String> RETURN_LIST_EXPECTED = List.of("NAME                AUTHOR           YEAR     USER ID",
                "small               author small     1988     ",
                "big                 author           1987     ",
                "really big name     author           1987     ");

        assertEquals(RETURN_LIST_EXPECTED,formatter.formatColumns());
    }
}