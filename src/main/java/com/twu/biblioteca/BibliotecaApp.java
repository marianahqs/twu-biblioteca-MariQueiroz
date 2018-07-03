package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "\n########### WELCOME TO BIBLIOTECA #############\n";
    private static List<Book> BOOKS;

    static {
        BOOKS = List.of(new Book("Name Book 1", "Author Book 1", 2018, true),
                new Book("Name Book 2", "Author Book 2", 2017, true),
                new Book("Name Book 3", "Author Book 3", 2003, false),
                new Book("Name Book 4", "Author Book 4", 1999, false));
    }


    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(BOOKS);

        showWelcomeMessage();

        UserInputScanner scanner = new UserInputScanner();
        MainMenu menu = new MainMenu(biblioteca,scanner);

        while (true) {
            menu.showMenuOptions();
            menu.handleUserOption(scanner.askUserInput());
        }
    }


    public static void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
