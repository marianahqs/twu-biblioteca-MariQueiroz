package com.twu.biblioteca.Components;

import java.util.Objects;

public class Book implements Item {
    private String name;
    private String author;
    private Integer year;
    private String userId;
    private boolean isAvailable;

    public Book(String name, String author, Integer year, boolean isAvailable) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString(){
        return String.format("%s / %s% / %d / %s",name,author, year);
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        Book otherBook = (Book) otherObject;
        return name.equals(otherBook.name) &&
                author.equals(otherBook.author) &&
                year.equals(otherBook.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, year);
    }

    public void checkoutBook() {
        isAvailable = false;
    }

    public void returnBook(){
        isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public String getUserId (){
        return userId;
    }


}

