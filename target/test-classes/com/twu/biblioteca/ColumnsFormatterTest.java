package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColumnsFormatterTest {
    List<Item> BOOKS = List.of(new Item("small", "author small", 1988, true),
            new Item("big","author", 1987, true),
            new Item("really big name", "author", 1987, true));
    ColumnsFormatter formatter = new ColumnsFormatter(BOOKS);

    @Test
    public void shouldFindTheSizeOfTheBiggestItem() {
        int SIZE_EXPECTED = 15;
        List<String> LIST_TO_TEST = List.of("small","big" ,"really big name");

        assertEquals(formatter.getSizeBiggestItem(LIST_TO_TEST), SIZE_EXPECTED);
    }

    @Test
    public void shouldFormatColumns() {
        List<String> RETURN_LIST_EXPECTED = List.of("small               author small     1988",
                "big                 author           1987",
                "really big name     author           1987");

        assertEquals(RETURN_LIST_EXPECTED,formatter.formatColumns());
    }
}