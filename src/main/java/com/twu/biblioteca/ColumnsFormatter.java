package com.twu.biblioteca;

import java.util.List;

public class ColumnsFormatter {

    private static List<Book> books;

    public ColumnsFormatter(List<Book> books) {
        this.books = books;
    }

    public Book formatColumns(){
        Book bookWithBiggestName = books.stream()
                .max((current, book) -> {
                    Integer length = current.getName().length();
                    return length.compareTo(book.getName().length());
                })
                .get();

        int length = bookWithBiggestName.getName().length();

        return bookWithBiggestName;

//        System.out.println(String.format("%-15s %-20s %s", BOOKS.get(i).getName(),
//                BOOKS.get(i).getAuthor(),
//                BOOKS.get(i).getYearPublished())); */
    }






}
