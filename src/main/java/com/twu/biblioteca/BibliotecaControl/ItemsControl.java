package com.twu.biblioteca.BibliotecaControl;

import com.twu.biblioteca.BibliotecaComponents.Item;

import java.util.List;
import java.util.NoSuchElementException;

public class ItemsControl {


    protected Item findItem(String nameOfItem, List<? extends Item> listToSearch) throws NoSuchElementException {
        try {
            return listToSearch.stream()
                    .filter(p -> p.getName().equals(nameOfItem))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            String message = String.format("That is not a valid %s name",
                    listToSearch.get(0).getClass().getSimpleName().toLowerCase()); //TODO Gambiarra
            throw new NoSuchElementException(message);
        }
    }

    protected boolean returnItem(Item itemToReturn) throws IllegalArgumentException {
        if (itemToReturn.isAvailable()){
            String message = String.format("That is not a valid %s to return", itemToReturn.getClass().getSimpleName().toLowerCase());
            throw new IllegalArgumentException(message);
        }
        itemToReturn.makeItAvailable();
        return true;
    }

    protected boolean checkoutItem(String loggedUserId, Item itemToCheckout) throws IllegalArgumentException  {
        if (!itemToCheckout.isAvailable()){
            String message = String.format("That %s is not available", itemToCheckout.getClass().getSimpleName().toLowerCase());
            throw new IllegalArgumentException(message);
        }
        itemToCheckout.makeItUnavailable(loggedUserId);
        return true;
    }
}
