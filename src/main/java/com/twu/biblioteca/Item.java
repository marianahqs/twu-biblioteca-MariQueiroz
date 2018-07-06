package com.twu.biblioteca;

import java.util.Objects;

public class Item {
    private String name;
    private String author;
    private Integer yearPublished;
    private boolean isAvailable;

    public Item(String name, String author, Integer yearPublished, boolean isAvailable) {
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

        Item otherItem = (Item) otherObject;
        return name.equals(otherItem.name) &&
                author.equals(otherItem.author) &&
                yearPublished.equals(otherItem.yearPublished);
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

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }
}
