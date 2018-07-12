package com.twu.biblioteca.Components;

import java.util.Objects;

public class Movie implements Item {
    private String name;
    private String director;
    private Integer year;
    private String rating;
    private String userId;
    private boolean isAvailable;

    public Movie(String name, String director, Integer year, String rating, boolean isAvailable) {
        this.name = name;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString(){
        return String.format("%s / %s% / %d / %s",name,director, year,rating);
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

        Movie otherBook = (Movie) otherObject;

        return name.equals(otherBook.name) &&
                director.equals(otherBook.director) &&
                year.equals(otherBook.year) &&
                rating.equals(otherBook.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,director, year, rating);
    }

    public void checkoutMovie() {
        isAvailable = false;
    }

    public void returnMovie(){
        isAvailable = true;
    }

    public String getName() {
        return name;
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

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }
}
