package com.twu.biblioteca;

import java.util.Scanner;

public class UserInputScanner {

    private Scanner keypad;

    public UserInputScanner() {
        this.keypad = new Scanner(System.in);
    }

    public String askUserInput() {
        return keypad.nextLine();
    }


}
