package com.twu.biblioteca;

import java.util.List;

public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "\n########### WELCOME TO BIBLIOTECA #############\n";
    private static List<Item> BOOKS;

    static {
        BOOKS = List.of(new Item("I Know Why The Caged Bird Sings","Angelou, Maya",1969,true),
        new Item("Parable of the Talents","Butler, Octavia",1998,true),
        new Item("Ponciá Vicêncio","Evaristo, Conceição",2003,true),
        new Item("Purple Hibiscus","Adichie, Chimamanda Ngozi",2003,true),
        new Item("Quarto de Despejo: diário de uma favelada","de Jesus, Carolina Maria",1960,true),
        new Item("Sobre-viventes!","da Silva, Cidinha",2016,true),
        new Item("The Color Purple","Walker, Alice",1982,true),
        new Item("The Meaning of Freedom: And Other Difficult Dialogues","Davis, Angela",2012,true),
        new Item("Um Defeito de Cor","Goncalves, Ana Maria",2006,true));
    }

    private static List<User> USERS = List.of(new User("123-4567", "ITAsTeacher", "Sonia Guimaraes", "7655-3434","sonia@mail.com"),
            new User("222-8765", "Astrophysicist", "Neil deGrasse Tyson", "3333-4456", "neil@email.com"),
            new User("345-9955", "PretaHacker", "Lorenna Villas Boas", "6565-0099","lorenna@email.com"));

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(BOOKS);
        UserInputScanner scanner = new UserInputScanner();
        UserManager userManager = new UserManager(USERS);
        MainMenu menu = new MainMenu(biblioteca,scanner, userManager);

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
