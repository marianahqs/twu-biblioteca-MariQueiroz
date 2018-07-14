package com.twu.biblioteca.UserIntarface;

import java.util.Scanner;

public class UserInputScanner {

    private Scanner keypad;

    public String askUserInput() {
        this.keypad = new Scanner(System.in);        
        return keypad.nextLine();
    }
}
