package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ColumnsFormatterTest {
    List<Item> BOOKS = List.of(Item.createBook("small", "author small", 1988, true),
            Item.createBook("big","author", 1987, true),
            Item.createBook("really big name", "author", 1987, true));

    List<Item> MOVIES = List.of(Item.createMovie("Name 1", "Director Movie 1", 1980,"8.1" ,true),
            Item.createMovie("Name Movie", "Director 2",2003,"none",false),
            Item.createMovie("Name 3", "Director Movie", 1970,"9", false),
            Item.createMovie("Name","Director",1999,"none",true));

    @Test
    public void shouldFindTheSizeOfTheBiggestItem() {
        ColumnsFormatter formatter = new ColumnsFormatter(BOOKS,"books");
        int SIZE_EXPECTED = 15;
        List<String> LIST_TO_TEST = List.of("small","big" ,"really big name");

        assertEquals(formatter.getSizeBiggestItem(LIST_TO_TEST), SIZE_EXPECTED);
    }

    @Test
    public void shouldFormatColumnsForBooksList() {
        ColumnsFormatter formatter = new ColumnsFormatter(BOOKS,"books");
        List<String> RETURN_LIST_EXPECTED = List.of("small               author small     1988",
                "big                 author           1987",
                "really big name     author           1987");

        assertEquals(RETURN_LIST_EXPECTED,formatter.formatColumns());
    }

    @Test
    public void shouldFormatColumnsForMoviesList() {
        ColumnsFormatter formatter = new ColumnsFormatter(MOVIES,"movies");
        List<String> RETURN_LIST_EXPECTED = List.of("Name 1         Director Movie 1     8.1      1980",
                "Name Movie     Director 2           none     2003",
                "Name 3         Director Movie       9        1970",
                "Name           Director             none     1999");

        assertEquals(RETURN_LIST_EXPECTED,formatter.formatColumns());
    }
}