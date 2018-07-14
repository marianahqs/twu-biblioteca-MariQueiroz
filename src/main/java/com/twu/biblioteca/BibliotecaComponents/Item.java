package com.twu.biblioteca.BibliotecaComponents;

public interface Item {
    boolean getIsAvailable();
    String getName();
    String getUserId();
    void makeItAvailable();
    void makeItUnavailable(String userId);
}
