package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColumnsFormatterTest {

    @Test
    void shouldFindTheSizeOfTheBiggestItem() {
        List<Book> books = List.of(new Book("small", "author small", 1988, true),
                new Book("big","author", 1987, true),
                new Book("really big name", "author", 1987, true));

       int SIZE_EXPECTED = 15;
       List<String> listToTest = List.of("small","big" ,"really big name");

        ColumnsFormatter formatter = new ColumnsFormatter(books);

        assertEquals(formatter.getSizeBiggestItem(listToTest), SIZE_EXPECTED);
    }

    @Test
    void shouldFormatColumns() {
        List<Book> books = List.of(new Book("small", "author small", 1988, true),
                new Book("big","author", 1987, true),
                new Book("really big name", "author", 1987, true));

       List<String> LIST_EXPECTED = List.of("small               author small     1988",
               "big                 author           1987",
               "really big name     author           1987");

        ColumnsFormatter formatter = new ColumnsFormatter(books);

            assertEquals(LIST_EXPECTED,formatter.formatColumns());
    }
}