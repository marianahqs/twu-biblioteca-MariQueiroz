package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        System.out.println("\n########### WELCOME TO BIBLIOTECA #############\n");

        List<Book> books = List.of(new Book("Livro 1", "Autor 1", 2018, true));

        Biblioteca biblioteca = new Biblioteca(books);
        System.out.println(mainMenu());

    }

    public static String mainMenu() {
        String mainMenuReturnString = "---- MAIN MENU ----\n(choose an option and insert its number)\n\n1 - List Books";

        return mainMenuReturnString;
    }


    public static String menuSelection(String s) {
        String menuSelectionReturnString = "Invalid Option! Please, select a valid option.";

        return menuSelectionReturnString;

    }

}
