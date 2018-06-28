package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


// old "I Know Why The Caged Bird Sings\tAngelou, Maya\t1969\nParable of the Talents\tButler, Octavia\t1998\nPonciá Vicêncio\tEvaristo, Conceição\t2003\nPurple Hibiscus\tAdichie, Chimamanda Ngozi\t2003\nQuarto de Despejo: diário de uma favelada\tde Jesus, Carolina Maria\t1960\nSobre-viventes!\tda Silva, Cidinha\t2016\nThe Color Purple\tWalker, Alice\t1982\nThe Meaning of Freedom: And Other Difficult Dialogues\tDavis, Angela\t2012\nUm Defeito de Cor\tGoncalves, Ana Maria\t2006"



public class BibliotecaTest {

    @Test
    public void testListBookFormat() {

        Biblioteca biblioteca = new Biblioteca();

        assertEquals("Name                                                    Author                      Year\nI Know Why The Caged Bird Sings	                        Angelou, Maya	            1969\nParable of the Talents	                                Butler, Octavia	            1998\nPonciá Vicêncio	                                        Evaristo, Conceição	        2003\nPurple Hibiscus	                                        Adichie, Chimamanda Ngozi	2003\nQuarto de Despejo: diário de uma favelada	            de Jesus, Carolina Maria	1960\nSobre-viventes!	                                        da Silva, Cidinha	        2016\nThe Color Purple	                                    Walker, Alice	            1982\nThe Meaning of Freedom: And Other Difficult Dialogues	Davis, Angela	            2012\nUm Defeito de Cor	                                    Goncalves, Ana Maria	    2006",biblioteca.listBooks());


    }

    @Test
    public void testMainMenuFormat() {

        Biblioteca biblioteca = new Biblioteca();

        assertEquals("---- MAIN MENU ----\n(choose an option and insert its number)\n\n1 - List Books",biblioteca.mainMenu());


    }
}

