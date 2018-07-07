package com.twu.biblioteca;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "\n########### WELCOME TO BIBLIOTECA #############\n";
    private static List<Item> BOOKS;
    private static List<Item> MOVIES;
    private static List<User> USERS;
    private static List<Item> ITEMS;

    static {
        BOOKS = List.of(Item.createBook("I Know Why The Caged Bird Sings", "Angelou, Maya", 1969, true),
                Item.createBook("Parable of the Talents", "Butler, Octavia", 1998, true),
                Item.createBook("Ponciá Vicêncio", "Evaristo, Conceição", 2003, true),
                Item.createBook("Purple Hibiscus", "Adichie, Chimamanda Ngozi", 2003, true),
                Item.createBook("Quarto de Despejo: diário de uma favelada", "de Jesus, Carolina Maria", 1960, true),
                Item.createBook("Sobre-viventes!", "da Silva, Cidinha", 2016, true),
                Item.createBook("The Color Purple", "Walker, Alice", 1982, true),
                Item.createBook("The Meaning of Freedom: And Other Difficult Dialogues", "Davis, Angela", 2012, true),
                Item.createBook("Um Defeito de Cor", "Goncalves, Ana Maria", 2006, true));

        MOVIES = List.of(Item.createMovie("The Secret Life of Bees", "Gina Prince-Bythewood", 2008, "8.5", true),
                Item.createMovie("Selma", "Ava DuVernay", 2014, "9", true),
                Item.createMovie("Twice as Nice", "Jessie Maple", 1989, "none", true),
                Item.createMovie("The Fantasia Barrino Story: Life Is Not a Fairy Tale", "Debbie Allen", 2006, "7.5", true),
                Item.createMovie("I Am Somebody", "Madeline Anderson", 1970, "8", true),
                Item.createMovie("Down in the Delta", "Maya Angelou", 1998, "9", true),
                Item.createMovie("Belle", "Amma Asante", 2013, "9", true));

        USERS = List.of(new User("123-4567", "ITAsTeacher", "Sonia Guimaraes", "7655-3434", "sonia@mail.com"),
                new User("222-8765", "Astrophysicist", "Neil deGrasse Tyson", "3333-4456", "neil@email.com"),
                new User("345-9955", "PretaHacker", "Lorenna Villas Boas", "6565-0099", "lorenna@email.com"));

        ITEMS = Stream.concat(BOOKS.stream(), MOVIES.stream())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(ITEMS);
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
