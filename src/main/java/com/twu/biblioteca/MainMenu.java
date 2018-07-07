package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MainMenu {

    private static final String LIST_BOOKS = "List Books";
    private static final String LIST_MOVIES = "List Movies";
    private static final String QUIT = "Quit";
    private static final String CHECKOUT_ITEM = "Checkout Item";
    private static final String RETURN_ITEM = "Return Item";
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
            "Checkout Item",
            "Return Item",
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

    public void handleUserOption(String userInput){

        try{
            String userSelection = getCurrentMenu().get(Integer.parseInt(userInput));

            switch (userSelection) {
                case (LIST_BOOKS):
                    try{
                        ColumnsFormatter booksListFormatter = new ColumnsFormatter(biblioteca.listBooks());
                        System.out.println("\n"+String.join("\n",booksListFormatter.formatColumns()));
                    } catch (NoSuchElementException exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case (LIST_MOVIES):
                    try{
                        ColumnsFormatter moviesListFormatter = new ColumnsFormatter(biblioteca.listMovies());
                        System.out.println("\n"+String.join("\n",moviesListFormatter.formatColumns()));
                    } catch (NoSuchElementException exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case (QUIT):
                    System.exit(0);

                case (CHECKOUT_ITEM):
                    try{
                        if (biblioteca.checkoutItem(askForInput("Enter the name of item to checkout:"))){
                            System.out.println("Thank you! Enjoy the book!");
                        }
                    } catch (NoSuchElementException | IllegalArgumentException exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case (RETURN_ITEM):
                    try{
                        if (biblioteca.returnItem(askForInput("Enter the name of item to return:"))){
                            System.out.println("Thank you for returning the book!");
                        }
                    } catch (NoSuchElementException | IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case (LOGIN):
                    try {
                        String userIdInput = askForInput("User ID:");
                        String passwordInput = askForInput("Password:");

                        userManager.login(userIdInput, passwordInput);

                    }catch (NoSuchElementException | IllegalArgumentException exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case(LOGOUT):
                    userManager.logout();
                    break;

                case(USER_INFORMATION):
                    System.out.println(userManager.getUserInformation());
            }
        } catch (IndexOutOfBoundsException iobExc){
            System.out.println("Select a valid option");
        }
    }

    public void showMenuOptions() {
        System.out.println(MAIN_MENU_HEADER);
        for (int index = 0; index<getCurrentMenu().size();index++){
            System.out.println(index+" - "+ getCurrentMenu().get(index));
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
