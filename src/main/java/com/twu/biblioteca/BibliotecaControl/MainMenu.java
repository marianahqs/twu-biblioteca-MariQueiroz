package com.twu.biblioteca.BibliotecaControl;

import com.twu.biblioteca.ColumnsFormatter.BooksColumnsFormatter;
import com.twu.biblioteca.ColumnsFormatter.MovieColumnsFormatter;
import com.twu.biblioteca.UserInputScanner;

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
    private static final List<String> MENU_LOGGED_OUT_OPTIONS = List.of("List Books",
            "List Movies",
            "Login",
            "Quit");

    private static final List<String> MENU_LOGGED_IN_OPTIONS = List.of("List Books",
            "List Movies",
            "Checkout Book",
            "Return Book",
            "Checkout Movie",
            "Return Movie",
            "User Information",
            "Logout",
            "Quit");

    private final UserInputScanner scanner;
    private final Biblioteca biblioteca;
    private final UserManager userManager;

    public MainMenu(Biblioteca biblioteca, UserInputScanner scanner, UserManager userManager) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;
        this.userManager = userManager;
    }

    public void handleUserOption(String userInput) {

        try {
            String userSelection = getCurrentMenu().get(Integer.parseInt(userInput));

            switch (userSelection) {
                case (LIST_BOOKS):
                    BooksColumnsFormatter booksListFormatter = new BooksColumnsFormatter(biblioteca.listBooks());
                    System.out.println("\n" + String.join("\n", booksListFormatter.formatColumns()));
                    break;

                case (LIST_MOVIES):
                    MovieColumnsFormatter moviesListFormatter = new MovieColumnsFormatter(biblioteca.listMovies());
                    System.out.println("\n" + String.join("\n", moviesListFormatter.formatColumns()));
                    break;

                case (QUIT):
                    System.exit(0);

                case (CHECKOUT_BOOK):
                    if (biblioteca.checkoutBook(askForInput("Enter the name of a book to makeItUnavailable:"), userManager.getUserLoggedID())) {
                        System.out.println("Thank you! Enjoy the book!");
                    }
                    break;

                case (RETURN_BOOK):
                    if (biblioteca.returnBook(askForInput("Enter the name of a book to return:"))) {
                        System.out.println("Thank you for returning the book!");
                    }
                    break;

                case (CHECKOUT_MOVIE):
                    if (biblioteca.checkoutMovie(askForInput("Enter the name of a movie to makeItUnavailable:"), userManager.getUserLoggedID())) {
                        System.out.println("Thank you! Enjoy the movie!");
                    }
                    break;

                case (RETURN_MOVIE):
                    if (biblioteca.returnMovie(askForInput("Enter the name of a movie to return:"))) {
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
        }
    }

    public void showMenuOptions() {
        System.out.println(MAIN_MENU_HEADER);
        List<String> currentMenuList = getCurrentMenu();
        for (int index = 0; index<currentMenuList.size();index++){
            System.out.println(index+" - "+ currentMenuList.get(index));
        }
    }

    private String askForInput(String question) {
        System.out.println("\n"+question);
        return scanner.askUserInput();
    }

    private List<String> getCurrentMenu(){
        List<String> returnListCurrentMenu = new ArrayList<>();
      //  returnListCurrentMenu.clear();  ?????

        if (userManager.isLoggedIn()){
            returnListCurrentMenu.addAll(MENU_LOGGED_IN_OPTIONS);

        } else {
            returnListCurrentMenu.addAll(MENU_LOGGED_OUT_OPTIONS);
        }
        return returnListCurrentMenu;
    }
}
