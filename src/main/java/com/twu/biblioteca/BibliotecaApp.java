package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaComponents.Book;
import com.twu.biblioteca.BibliotecaComponents.Movie;
import com.twu.biblioteca.BibliotecaComponents.User;
import com.twu.biblioteca.BibliotecaControl.BooksControl;
import com.twu.biblioteca.BibliotecaControl.MoviesControl;
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
        BOOKS = List.of(new Book("I Know Why The Caged Bird Sings", "Angelou, Maya", "1969", true, null),
                new Book("Parable of the Talents", "Butler, Octavia", "1998", true, null),
                new Book("Ponciá Vicêncio", "Evaristo, Conceição", "2003", true, null),
                new Book("Purple Hibiscus", "Adichie, Chimamanda Ngozi", "2003", false, "xxxxxx"),
                new Book("Quarto de Despejo: diário de uma favelada", "de Jesus, Carolina Maria", "1960", true, null),
                new Book("Sobre-viventes!", "da Silva, Cidinha", "2016", true, null),
                new Book("The Color Purple", "Walker, Alice", "1982", true, null),
                new Book("The Meaning of Freedom: And Other Difficult Dialogues", "Davis, Angela", "2012", true, null),
                new Book("Um Defeito de Cor", "Goncalves, Ana Maria", "2006", true, null));

        MOVIES = List.of(new Movie("The Secret Life of Bees", "Gina Prince-Bythewood", "2008", "8.5", true, null),
                new Movie("Selma", "Ava DuVernay", "2014", "9", true, null),
                new Movie("Twice as Nice", "Jessie Maple", "1989", "none", true, null),
                new Movie("The Fantasia Barrino Story: Life Is Not a Fairy Tale", "Debbie Allen", "2006", "7.5", true, null),
                new Movie("I Am Somebody", "Madeline Anderson", "1970", "8", true, null),
                new Movie("Down in the Delta", "Maya Angelou", "1998", "9", false, "zzzzzz"),
                new Movie("Belle", "Amma Asante", "2013", "9", true, null));

        USERS = List.of(new User("123-4567", "ITAsTeacher", "Sonia Guimaraes", "7655-3434", "sonia@mail.com","customer"),
                new User("222-8765", "Astrophysicist", "Neil deGrasse Tyson", "3333-4456", "neil@email.com","customer"),
                new User("345-9955", "PretaHacker", "Lorenna Villas Boas", "6565-0099", "lorenna@email.com", "customer"),
                new User("345-8760", "librarian", "Librarian da Silva", "7655-7545", "librarian@email.com", "librarian"));
    }

    public static void main(String[] args) {
        BooksControl booksControl = new BooksControl(BOOKS);
        MoviesControl moviesControl = new MoviesControl(MOVIES);
        UserInputScanner scanner = new UserInputScanner();
        UserManager userManager = new UserManager(USERS);
        MainMenu menu = new MainMenu(booksControl,moviesControl,scanner, userManager);

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
