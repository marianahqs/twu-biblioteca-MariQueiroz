package com.twu.biblioteca.BibliotecaComponents;

import java.util.Objects;

public class Movie extends Item {
    private String director;
    private String rating;

    public Movie() {
    }

    public Movie(String name, String director, String year, String rating, boolean isAvailable, String userId) {
        super(name, year, isAvailable,userId );
        this.director = director;
        this.rating = rating;
    }


    @Override
    public String toString(){
        return String.format("%s / %s% / %d / %s",super.getName(),director, super.getYear(),rating);
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }

        Movie otherBook = (Movie) otherObject;

        return super.getName().equals(otherBook.getName()) &&
                director.equals(otherBook.director) &&
                super.getYear().equals(otherBook.getYear()) &&
                rating.equals(otherBook.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.getName(),director, super.getYear(), rating);
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }
}
