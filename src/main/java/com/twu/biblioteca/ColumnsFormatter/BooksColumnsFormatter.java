package com.twu.biblioteca.ColumnsFormatter;

import com.twu.biblioteca.Components.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BooksColumnsFormatter {

    private static List<Book> books;

    public BooksColumnsFormatter(List<Book> books) {
        this.books = books;
    }

    public List<String> formatColumns(){
        int COLUMN_DISTANCE = 5;
        List<String > returnList = new ArrayList<>();

        int nameColumnSize = getSizeBiggestItem(books.stream().
                map(Book::getName).
                collect(Collectors.toList()))
                +COLUMN_DISTANCE;

        int authorColumnSize = getSizeBiggestItem(books.stream().
                map(Book::getAuthor).
                collect(Collectors.toList()))
                +COLUMN_DISTANCE;


        for (int line = 0; line < books.size(); line++) {

            returnList.add(String.format("%-" + nameColumnSize + "s%-" + authorColumnSize + "s%s",
                    books.get(line).getName(),
                    books.get(line).getAuthor(),
                    books.get(line).getYear()));
        }

        return returnList;
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
