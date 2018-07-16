package com.twu.biblioteca;

import com.twu.biblioteca.UserIntarface.ColumnsFormatter;
import com.twu.biblioteca.BibliotecaComponents.Book;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BooksColumnsFormatterTest {
    List<Book> BOOKS = List.of(new Book("small", "author small", "1988", true, null),
            new Book("big","author", "1987", true, null),
            new Book("really big name", "author", "1987", true, null));
    List<String> listFields = List.of("getName","getAuthor","getYear","getUserId");

  //  @Test
    public void shouldFindTheSizeOfTheBiggestItem() {
        ColumnsFormatter<Book> formatter = new ColumnsFormatter<>(BOOKS,listFields);
        int SIZE_EXPECTED = 15;
        List<String> LIST_TO_TEST = List.of("small","big" ,"really big name");

     //   assertEquals(formatter.getSizeOfLongestItem(LIST_TO_TEST), SIZE_EXPECTED); TODO became private, can't test anymore
    }

    @Test
    public void shouldFormatColumnsForBooksList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        ColumnsFormatter<Book> formatter = new ColumnsFormatter<>(BOOKS,listFields);
        List<String> RETURN_LIST_EXPECTED = List.of("small               author small     1988          ",
                "big                 author           1987          ",
                "really big name     author           1987          ");

        assertEquals(RETURN_LIST_EXPECTED,formatter.formatColumns());
    }
}