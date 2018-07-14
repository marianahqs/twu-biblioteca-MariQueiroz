package com.twu.biblioteca.BibliotecaComponents;

public class Item {

    private String name;
    private String year;
    private String userId;
    private boolean isAvailable;

    public Item (){

    }

    public Item(String name, String year, boolean isAvailable, String userId) {
        this.name = name;
        this.year = year;
        this.isAvailable = isAvailable;
        this.userId = userId;
    }

    public void makeItUnavailable(String userId) {
        isAvailable = false;
        this.userId = userId;
    }

    public void makeItAvailable(){
        isAvailable = true;
        this.userId = null;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public String getUserId (){
        return userId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}
