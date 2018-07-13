package com.twu.biblioteca.Components;

public interface Item {
    boolean getIsAvailable();
    String getName();
    String getUserId();
    void makeItAvailable();
    void makeItUnavailable(String userId);
}
