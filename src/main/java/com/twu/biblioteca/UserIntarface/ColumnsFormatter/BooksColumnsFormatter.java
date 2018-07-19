package com.twu.biblioteca.UserIntarface.ColumnsFormatter;

import com.twu.biblioteca.BibliotecaComponents.Book;

import java.util.*;
import java.util.function.Function;

public class BooksColumnsFormatter extends ItemsColumnsFormatter<Book> {
    private static final LinkedHashMap<String, Function<Book, String>> COLUMNS = new LinkedHashMap<String, Function<Book, String>>() {{
        put("NAME", (Book::getName));
        put("AUTHOR", (Book::getAuthor));
        put("YEAR", (Book::getYear));
        put("USER ID", (Book::getUserId));
    }};

    public BooksColumnsFormatter(List<Book> items) {
        super(items, COLUMNS);
    }
}
