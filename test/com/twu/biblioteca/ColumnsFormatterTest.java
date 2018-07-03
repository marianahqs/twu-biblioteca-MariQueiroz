package com.twu.biblioteca;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ColumnsFormatterTest {

    @Test
    void shouldFindTheBookWithBiggestName() {
        Book biggestName = new Book("small", "author", 1988, true);
        List<Book> books = List.of(biggestName,
                new Book("big", "author", 1987, true),
                new Book("really big name", "author", 1987, true));

        ColumnsFormatter formatter = new ColumnsFormatter(books);

        assertThat(formatter.formatColumns(), is(biggestName));
    }
}