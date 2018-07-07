package com.twu.biblioteca;

import java.util.Objects;

public class Item {
    private String name;
    private String author;
    private String director;
    private Integer year;
    private String rating;
    private String kind;
    private String userId;
    private boolean isAvailable;

    private Item(String name, String author, String director, Integer year, String rating, boolean isAvailable, String kind) {
        this.name = name;
        this.author = author;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.isAvailable = isAvailable;
        this.kind = kind;
    }

    public static Item createBook(String name, String author,int year, boolean isAvailable){
        return new Item(name,author,null,year,null,isAvailable, "book");
    }

    public static Item createMovie(String name, String director,int year, String rating, boolean isAvailable){
        return new Item(name,null,director,year,rating,isAvailable, "movie");
    }

    @Override
    public String toString(){
        if (kind.equals("book")){
            return String.format("%s / %s% / %d / %s",name,author, year);
        }else{
            return String.format("%s / %s% / %d / %s",name,director, year,rating);
        }
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
        if(kind.equals("book")){
            return name.equals(otherItem.name) &&
                    author.equals(otherItem.author) &&
                    year.equals(otherItem.year);
        }else{
            return name.equals(otherItem.name) &&
                    director.equals(otherItem.director) &&
                    year.equals(otherItem.year) &&
                    rating.equals(otherItem.rating);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author,director, year, rating);
    }

    public void checkoutItem() {
        isAvailable = false;
    }

    public void returnItem(){
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

    public String getKind(){
        return kind;
    }
}
