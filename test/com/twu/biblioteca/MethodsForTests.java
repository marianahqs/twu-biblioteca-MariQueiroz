package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaComponents.Item;

import java.util.List;
import java.util.NoSuchElementException;

public class MethodsForTests {
    public MethodsForTests() {
    }

    public static Item findItem(String nameOfItem, List<? extends Item> listToSearch) throws NoSuchElementException {
        try {
            return listToSearch.stream()
                    .filter(p -> p.getName().equals(nameOfItem))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException nexc) {
            String message = String.format("That is not a valid %s name",
                    listToSearch.get(0).getClass().getSimpleName().toLowerCase());
            throw new NoSuchElementException(message);
        }
    }
}