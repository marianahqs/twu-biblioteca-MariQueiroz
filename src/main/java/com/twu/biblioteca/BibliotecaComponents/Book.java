package com.twu.biblioteca.BibliotecaComponents;

import java.util.Objects;

public class Book extends Item{
    private String author;

    public Book(){     //TODO Is it necessary? Why?
        super();
    }

    public Book(String name, String author, String year, boolean isAvailable, String userId) {
        super(name, year, isAvailable, userId);
        this.author = author;
    }

    @Override
    public String toString(){
        return String.format("%s / %s% / %d / %s",super.getName(),author, super.getYear());
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
        return super.getName().equals(otherBook.getName()) &&
                author.equals(otherBook.author) &&
                super.getYear().equals(otherBook.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getName(), author, super.getYear());
    }

    public String getAuthor() {
        return author;
    }

}

