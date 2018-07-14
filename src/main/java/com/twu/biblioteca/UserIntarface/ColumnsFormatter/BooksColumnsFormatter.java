package com.twu.biblioteca.UserIntarface.ColumnsFormatter;

import com.twu.biblioteca.BibliotecaComponents.Book;
import com.twu.biblioteca.BibliotecaComponents.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;

public class BooksColumnsFormatter {

    private static List<Book> books;

    public BooksColumnsFormatter(List<Book> books) {
        this.books = books;
    }

    public List<String> formatColumns(){
        int COLUMN_DISTANCE = 5;
        List<String > returnList = new ArrayList<>();

        int nameColumnSize = getColumnSize(COLUMN_DISTANCE, Book::getName);
        int authorColumnSize = getColumnSize(COLUMN_DISTANCE, Book::getAuthor);
        int yearColumnSize = getColumnSize(COLUMN_DISTANCE, Book::getYear);


        for (Book book : books) {

            returnList.add(String.format("%-" + nameColumnSize +
                            "s%-" + authorColumnSize +
                            "s%-" + yearColumnSize + "s%s",
                    book.getName(),
                    book.getAuthor(),
                    book.getYear(),
                    ofNullable(book.getUserId()).orElse("")));
        }

        return returnList;
    }

    private int getColumnSize(int COLUMN_DISTANCE, Function<Book, String> mapItem) {
        return getSizeBiggestItem(books.stream().
                    map(mapItem).
                    collect(Collectors.toList()))
                    +COLUMN_DISTANCE;
    }

    public int getSizeBiggestItem(List<String> listToTest)throws NoSuchElementException {
        try {
            String maxString = listToTest.stream().max((current, other) -> {
                Integer length = current.length();
                return length.compareTo(other.length());
            }).get();

            return maxString.length();
        }catch (NoSuchElementException noItem){
            throw new NoSuchElementException("Books list is empty");
        }
    }
}
