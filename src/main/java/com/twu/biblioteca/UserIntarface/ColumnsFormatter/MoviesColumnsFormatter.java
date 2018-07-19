package com.twu.biblioteca.UserIntarface.ColumnsFormatter;

import com.twu.biblioteca.BibliotecaComponents.Movie;

import java.util.LinkedHashMap;
import java.util.List;

import java.util.function.Function;

public class MoviesColumnsFormatter extends ItemsColumnsFormatter<Movie> {
        private static final LinkedHashMap<String, Function<Movie, String>> COLUMNS = new LinkedHashMap<String, Function<Movie, String>>() {{
            put("NAME", (Movie::getName));
            put("DIRECTOR", (Movie::getDirector));
            put("RATING", (Movie::getRating));
            put("YEAR", (Movie::getYear));
            put("USER ID", (Movie::getUserId));
        }};

        public MoviesColumnsFormatter(List<Movie> items) {
            super(items, COLUMNS);
        }
}
