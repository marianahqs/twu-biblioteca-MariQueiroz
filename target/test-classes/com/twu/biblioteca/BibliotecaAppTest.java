package com.twu.biblioteca;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

public class BibliotecaAppTest {

    //Config Print Test
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void shouldPrintWelcomeMessage() {
        BibliotecaApp.showWelcomeMessage();
        assertEquals("\n########### WELCOME TO BIBLIOTECA #############\n\n",systemOutRule.getLog());
    }
}
