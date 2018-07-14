package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaComponents.Book;
import com.twu.biblioteca.BibliotecaComponents.Movie;
import com.twu.biblioteca.BibliotecaComponents.User;
import com.twu.biblioteca.BibliotecaControl.Biblioteca;
import com.twu.biblioteca.BibliotecaControl.UserManager;
import com.twu.biblioteca.UserIntarface.MainMenu;
import com.twu.biblioteca.UserIntarface.UserInputScanner;

import java.util.List;

public class BibliotecaApp {

    private static final String WELCOME_MESSAGE = "\n########### WELCOME TO BIBLIOTECA #############\n";
    private static List<Book> BOOKS;
    private static List<Movie> MOVIES;
    private static List<User> USERS;

    static {
        BOOKS = List.of(new Book("I Know Why The Caged Bird Sings", "Angelou, Maya", "1969", true),
                new Book("Parable of the Talents", "Butler, Octavia", "1998", true),
                new Book("Ponciá Vicêncio", "Evaristo, Conceição", "2003", true),
                new Book("Purple Hibiscus", "Adichie, Chimamanda Ngozi", "2003", true),
                new Book("Quarto de Despejo: diário de uma favelada", "de Jesus, Carolina Maria", "1960", true),
                new Book("Sobre-viventes!", "da Silva, Cidinha", "2016", true),
                new Book("The Color Purple", "Walker, Alice", "1982", true),
                new Book("The Meaning of Freedom: And Other Difficult Dialogues", "Davis, Angela", "2012", true),
                new Book("Um Defeito de Cor", "Goncalves, Ana Maria", "2006", true));

        MOVIES = List.of(new Movie("The Secret Life of Bees", "Gina Prince-Bythewood", "2008", "8.5", true),
                new Movie("Selma", "Ava DuVernay", "2014", "9", true),
                new Movie("Twice as Nice", "Jessie Maple", "1989", "none", true),
                new Movie("The Fantasia Barrino Story: Life Is Not a Fairy Tale", "Debbie Allen", "2006", "7.5", true),
                new Movie("I Am Somebody", "Madeline Anderson", "1970", "8", true),
                new Movie("Down in the Delta", "Maya Angelou", "1998", "9", true),
                new Movie("Belle", "Amma Asante", "2013", "9", true));

        USERS = List.of(new User("123-4567", "ITAsTeacher", "Sonia Guimaraes", "7655-3434", "sonia@mail.com","customer"),
                new User("222-8765", "Astrophysicist", "Neil deGrasse Tyson", "3333-4456", "neil@email.com","customer"),
                new User("345-9955", "PretaHacker", "Lorenna Villas Boas", "6565-0099", "lorenna@email.com", "customer"),
                new User("345-9955", "librarian", "Librarian da Silva", "7655-7545", "librarian@email.com", "librarian"));
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca(BOOKS,MOVIES);
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
