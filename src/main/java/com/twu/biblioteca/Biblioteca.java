package com.twu.biblioteca;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Biblioteca {

    private final List<Item> items;

    public Biblioteca(List<Item> items) {
        this.items = items;
    }

    public List<Item> listBooks() {
        return items.stream().filter(p -> p.getIsAvailable() && p.getKind().equals("book")).collect(Collectors.toList());
    }

    public List<Item> listMovies() {
        return items.stream().filter(p -> p.getIsAvailable()&& p.getKind().equals("movie")).collect(Collectors.toList());
    }

    public boolean checkoutItem(String nameOfBookToCheckout) throws NoSuchElementException, IllegalArgumentException {
        boolean returnFlag = true;

        Item itemToCheckout = getBook(nameOfBookToCheckout);

        if (itemToCheckout.getIsAvailable()){
            itemToCheckout.checkoutItem();
        } else{
            throw new IllegalArgumentException("That item is not available");
        }
        return returnFlag;
    }


    public boolean returnItem(String nameOfBookToReturn) throws NoSuchElementException, IllegalArgumentException {
        boolean returnFlag = true;
        Item itemToReturn = getBook(nameOfBookToReturn);

        if (itemToReturn.getIsAvailable()){
            throw new IllegalArgumentException("That is not a valid item to return");
        }
        itemToReturn.returnItem();
        return returnFlag;
    }


    public Item getBook(String nameOfItem) throws NoSuchElementException {
        try {
            return items.stream()
                    .filter(p -> p.getName().equals(nameOfItem))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            throw new NoSuchElementException("That is not a valid item name");
        }
    }

}
