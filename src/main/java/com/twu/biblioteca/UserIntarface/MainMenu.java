package com.twu.biblioteca.UserIntarface;

import com.twu.biblioteca.BibliotecaComponents.Book;
import com.twu.biblioteca.BibliotecaComponents.Movie;
import com.twu.biblioteca.BibliotecaControl.BooksControl;
import com.twu.biblioteca.BibliotecaControl.MoviesControl;
import com.twu.biblioteca.BibliotecaControl.UserManager;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MainMenu {

    private static final String LIST_BOOKS = "List Books";
    private static final String LIST_MOVIES = "List Movies";
    private static final String QUIT = "Quit";
    private static final String CHECKOUT_BOOK = "Checkout Book";
    private static final String RETURN_BOOK = "Return Book";
    private static final String CHECKOUT_MOVIE = "Checkout Movie";
    private static final String RETURN_MOVIE = "Return Movie";
    private static final String LOGIN = "Login";
    private static final String LOGOUT = "Logout";
    private static final String USER_INFORMATION = "User Information";
    private static final String MAIN_MENU_HEADER = "\n\n---- MAIN MENU ---- \n(choose an option and insert its number)\n";
    private static final List<String> MENU_LOGGED_OUT_OPTIONS = List.of(LIST_BOOKS,
            LIST_MOVIES,
            LOGIN,
            QUIT);

    private static final List<String> MENU_LOGGED_IN_OPTIONS = List.of(LIST_BOOKS,
            LIST_MOVIES,
            CHECKOUT_BOOK,
            RETURN_BOOK,
            CHECKOUT_MOVIE,
            RETURN_MOVIE,
            USER_INFORMATION,
            LOGOUT,
            QUIT);

    private final UserInputScanner scanner;
    private final BooksControl booksControl;
    private final MoviesControl moviesControl;
    private final UserManager userManager;

    public MainMenu(BooksControl booksControl, MoviesControl moviesControl, UserInputScanner scanner, UserManager userManager) {
        this.booksControl = booksControl;
        this.scanner = scanner;
        this.moviesControl = moviesControl;
        this.userManager = userManager;
    }

    public void handleUserOption(String userInput) {

        try {
            String userSelection = getCurrentMenu().get(Integer.parseInt(userInput));

            switch (userSelection) {
                case (LIST_BOOKS):
                    listBooks();
                    break;

                case (LIST_MOVIES):
                    listMovies();
                    break;

                case (QUIT):
                    System.exit(0);

                case (CHECKOUT_BOOK):
                    if (booksControl.checkoutBook(askForInput("Enter the name of a book to checkout:"), userManager.getUserLoggedID())) {
                        System.out.println("Thank you! Enjoy the book!");
                    }
                    break;

                case (RETURN_BOOK):
                    if (booksControl.returnBook(askForInput("Enter the name of a book to return:"))) {
                        System.out.println("Thank you for returning the book!");
                    }
                    break;

                case (CHECKOUT_MOVIE):
                    if (moviesControl.checkoutMovie(askForInput("Enter the name of a movie to checkout:"), userManager.getUserLoggedID())) {
                        System.out.println("Thank you! Enjoy the movie!");
                    }
                    break;

                case (RETURN_MOVIE):
                    if (moviesControl.returnMovie(askForInput("Enter the name of a movie to return:"))) {
                        System.out.println("Thank you for returning the movie!");
                    }
                    break;

                case (LOGIN):
                        userManager.login(askForInput("User ID:"),askForInput("Password:"));
                    break;

                case (LOGOUT):
                    userManager.logout();
                    break;

                case (USER_INFORMATION):
                    System.out.print(userManager.getUserInformation());
                    break;

                default:
                    System.out.println("Select a valid option");
            }
        } catch (IndexOutOfBoundsException | NumberFormatException iobExc) {
            System.out.println("Select a valid option");
        } catch (NoSuchElementException | IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        } catch (NoSuchMethodException | IllegalAccessException |InvocationTargetException | NullPointerException exp){
            System.out.println("Column Formatter error"); //TODO same message inside ColumnsFormatter class
        }
    }

    private void listMovies() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Movie> movies = moviesControl.listAvailableMovies();

        if (userManager.isLibrarian()) {
            movies = moviesControl.listAllMovies();
        }
            ColumnsFormatter<Movie> moviesListFormatter = new ColumnsFormatter<>(movies,Movie.FIELD_LIST);
            System.out.println("\n" + String.join("\n", moviesListFormatter.formatColumns()));
    }

    private void listBooks() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Book> books = booksControl.listAvailableBooks();

        if (userManager.isLibrarian()) {
            books = booksControl.listAllBooks();
        }

        ColumnsFormatter<Book> booksListFormatter = new ColumnsFormatter<>(books, Book.FIELDS_LIST);
        System.out.println("\n" + String.join("\n", booksListFormatter.formatColumns()));
    }

    public void showMenuOptions() {
        System.out.println(MAIN_MENU_HEADER);
        List<String> currentMenuList = getCurrentMenu();
        for (int index = 0; index < currentMenuList.size(); index++) {
            System.out.println(index + " - " + currentMenuList.get(index));
        }
    }

    private String askForInput(String question) {
        System.out.println("\n"+question);
        return scanner.askUserInput();
    }

    private List<String> getCurrentMenu(){
        List<String> returnListCurrentMenu = new ArrayList<>();

        if (userManager.isLoggedIn()){
            returnListCurrentMenu.addAll(MENU_LOGGED_IN_OPTIONS);

        } else {
            returnListCurrentMenu.addAll(MENU_LOGGED_OUT_OPTIONS);
        }
        return returnListCurrentMenu;
    }
}
