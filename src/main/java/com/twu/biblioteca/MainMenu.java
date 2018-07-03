package com.twu.biblioteca;

import java.util.List;
import java.util.NoSuchElementException;

public class MainMenu {
    private static final String LIST_BOOKS = "1";
    private static final String EXIT_OPTION = "4";
    private static final String CHECKOUT_BOOK = "2";
    private static final String RETURN_BOOK = "3";
    private static final String MAIN_MENU_HEADER = "\n\n---- MAIN MENU ---- \n(choose an option and insert its number)\n";
    private static final List<String> MENU_OPTIONS = List.of("1 - List Books",
            "2 - Checkout Book",
            "3 - Return Book",
            "4 - Exit");

    private final UserInputScanner scanner;

    private final Biblioteca biblioteca;

    public MainMenu(Biblioteca biblioteca, UserInputScanner scanner) {
        this.biblioteca = biblioteca;
        this.scanner = scanner;
    }


    public void handleUserOption(String userInput) {
        switch (userInput) {
            case (LIST_BOOKS):
                System.out.println(biblioteca.listBooks());
                break;

            case (EXIT_OPTION):
                System.exit(0);

            case (CHECKOUT_BOOK):
                try{
                    if (biblioteca.checkoutBook(askNameOfBook())){
                        System.out.println("Thank you! Enjoy the book!");
                    }
                } catch (NoSuchElementException nseException){
                    System.out.println(nseException.getMessage());

                }catch (IllegalArgumentException iaeException){
                    System.out.println(iaeException.getMessage());
                }
                break;

            case (RETURN_BOOK):
                try{
                    if (biblioteca.returnBook(askNameOfBook())){
                        System.out.println("Thank you for returning the book!");
                    }
                } catch (NoSuchElementException nseException) {
                    System.out.println(nseException.getMessage());
                }catch (IllegalArgumentException iaeException){
                    System.out.println(iaeException.getMessage());
                }
                break;
        }
    }

//    private void checkoutLoop() {
//        boolean checkoutReturnError = true;
//
//        while (checkoutReturnError) {
//            String nameOfBook = askNameOfBook();
//            if (nameOfBook.equals("1")) {
//                break;
//            } else {
//                checkoutReturnError = !biblioteca.checkoutBook(nameOfBook);
//            }
//        }
//    }
//
//    private void returnLoop() {
//        boolean returnReturnError = true;
//
//        while (returnReturnError) {
//            String nameOfBook = askNameOfBook();
//            if (nameOfBook.equals("1")) {
//                break;
//            } else {
//                returnReturnError = !biblioteca.returnBook(nameOfBook);
//            }
//        }
//    }

    public void showMenuOptions() {
        System.out.println(MAIN_MENU_HEADER);

        System.out.println(String.join("\n", MENU_OPTIONS));
    }

    private String askNameOfBook() {
        System.out.println("\nWhat is the book's name? (Type '1' to quit)");

        return scanner.askUserInput();
    }

}
