package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "\n########### WELCOME TO BIBLIOTECA #############\n";
    private static List<Book> BOOKS;

    static {
        BOOKS = List.of(new Book("I Know Why The Caged Bird Sings","Angelou, Maya",1969,true),
        new Book("Parable of the Talents","Butler, Octavia",1998,true),
        new Book("Ponciá Vicêncio","Evaristo, Conceição",2003,true),
        new Book("Purple Hibiscus","Adichie, Chimamanda Ngozi",2003,true),
        new Book("Quarto de Despejo: diário de uma favelada","de Jesus, Carolina Maria",1960,true),
        new Book("Sobre-viventes!","da Silva, Cidinha",2016,true),
        new Book("The Color Purple","Walker, Alice",1982,true),
        new Book("The Meaning of Freedom: And Other Difficult Dialogues","Davis, Angela",2012,true),
        new Book("Um Defeito de Cor","Goncalves, Ana Maria",2006,true));
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(BOOKS);
        UserInputScanner scanner = new UserInputScanner();
        MainMenu menu = new MainMenu(biblioteca,scanner);

        showWelcomeMessage();

        while (true) {
            menu.showMenuOptions();
            menu.handleUserOption(scanner.askUserInput());
        }
    }

    public static void showWelcomeMessage() {
        System.out.println(WELCOME_MESSAGE);
    }
}
