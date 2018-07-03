package com.twu.biblioteca;

import java.util.Objects;

public class Book {
    private String name;
    private String author;
    private Integer yearPublished;
    private boolean isAvailable;

    public Book(String name, String author, Integer yearPublished, boolean isAvailable) {
        this.name = name;
        this.author = author;
        this.yearPublished = yearPublished;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString(){
        return String.format("%s / %s / %d",name,author,yearPublished);
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
                yearPublished.equals(otherBook.yearPublished);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, yearPublished);
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

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYearPublished() {
        return yearPublished;
    }

    public void setYearPublished(Integer yearPublished) {
        this.yearPublished = yearPublished;
    }
}
