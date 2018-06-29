package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class BibliotecaApp {

    private static List<Book> books;

    static {
        books = List.of(new Book("Name Book 1", "Author Book 1", 2018, true),
                new Book("Name Book 2", "Author Book 2", 2017, true),
                new Book("Name Book 3", "Author Book 3", 2003, false),
                new Book("Name Book 4", "Author Book 4", 1999, false));
    }


    public static void main(String[] args) {
        System.out.println("\n########### WELCOME TO BIBLIOTECA #############\n");
        Scanner keypad = new Scanner(System.in);
        System.out.println(mainMenu());
        String selection = keypad.next();

        System.out.println(menuSelection(selection));


    }

    public static String mainMenu() {
        String menuHeader = "---- MAIN MENU ---- \n(choose an option and insert its number)\n\n";
        List<String> mainMenu = List.of("1 - List Books\n2 - Exit");

        return menuHeader+ String.join("\n",mainMenu);

    }


    public static List<Book> menuSelection(String selection) {
        Biblioteca biblioteca = new Biblioteca(books);
        List<Book> returnList = List.of();

        switch (selection){
            case "1":
                returnList = biblioteca.listBooks();
                break;
            case "2":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid selection! Please, select a valid option.");
        }
        return returnList;

    }

}
