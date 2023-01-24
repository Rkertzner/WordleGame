package org.cis1200.wordle;
import org.junit.jupiter.api.*;


import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * You can use this file (and others) to test your
 * implementation.
 */

public class WordleTest {
    @Test
    public void test() {
        assertNotEquals("CIS 120", "CIS 160");
    }

    @Test
    public void testWordScanner() {
        Wordle word = new Wordle();
        assertTrue(word.wordExists(word.getHiddenWord()) && word.wordExists(word.getRandomWord()));
    }
    @Test
    public void testWordleGetters() {
        Wordle word = new Wordle();
        assertNotEquals(null, word.getCell(0, 0));
    }

    @Test
    public void testIsValid() {
        Wordle word = new Wordle();
        assertFalse(word.isValid("dog"));
        assertTrue(word.isValid("ideal"));
    }

    @Test
    public void testPlayTurn() {
        Wordle word = new Wordle();
        word.setHiddenWord("slang");
        word.playTurn("ideal");
        Tile cellZero = word.getCell(0,0);
        Tile cellThree = word.getCell(3,0);
        assertEquals('i', cellZero.getChar());
        assertEquals(cellZero.getBackground(), new Color(185, 95, 120));
        assertEquals(cellThree.getBackground(), new Color(200, 182, 83));
    }

    @Test
    public void testGameOver() {
        Wordle word = new Wordle();
        word.setHiddenWord("ideal");
        word.playTurn("ideal");
        assertTrue(word.getWon());
        Tile[] newBoard = new Tile[5];
        newBoard = word.getRowTiles();
        for (Tile tile : newBoard) {
            assertEquals(tile.getBackground(), new Color(108, 169, 101));
        }
    }

}

