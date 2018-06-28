package com.twu.biblioteca;

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

    public boolean isAvailable() {
        return this.isAvailable;
    }
}
