package org.cis1200.wordle;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Wordle {


    private Tile[][] board;

    private static String[] words;
    private String hiddenWord;

    private static ArrayList<String> usedGuesses;

    private String userGuess;

    private boolean gameOver;
    private char[] wordChars;
    private int row;
    private int column;

    public Wordle() {
        reset();
    }

    public void reset() {
        row = 0;
        column = 0;
        gameOver = false;
        board = new Tile[6][5];
        words = wordScanner();
        hiddenWord = getRandomWord();
        usedGuesses = new ArrayList<>();
        wordChars = new char[5];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                board[i][j] = new Tile();
            }
        }
    }

    public String[] wordScanner() {
        // to handle exceptions include throws
        Set<String> listOfStrings = new TreeSet<>();
        // list that holds strings of a file
        try {
            BufferedReader bf = new BufferedReader(new FileReader("files/words.txt"));

            // read entire line as string
            String line = bf.readLine();
            while ((line = bf.readLine()) != null) {
                listOfStrings.add(line);
            }
            // closing bufferreader object
            bf.close();
            return listOfStrings.toArray(new String[0]);
        } catch (IOException e) {
            throw new NoSuchElementException();
        }
    }

    public String getRandomWord() {
        Random rand = new Random();
        int num = rand.nextInt(words.length);
        return words[num];
    }

    public boolean wordExists(String guess) {
        boolean bool = false;
        for (String elm : words) {
            if (guess.equals(elm)) {
                bool = true;
                break;
            }
        }
        return bool;
    }

    public boolean unusedWord(String guess) {
        boolean unused = true;
        for (String word : usedGuesses) {
            if (word.equals(guess)) {
                unused = false;
                break;
            }
        }
        return unused;
    }

    public void addGuess(String guess) {
        usedGuesses.add(guess);
    }

    public boolean isValid(String guess) {
        return guess.length() == 5 && wordExists(guess) && unusedWord(guess);
    }

    public void playTurn(String guess) {
        addGuess(guess);
        if (guess.equals(hiddenWord)) {
            for (int i = 0; i < hiddenWord.length(); i++) {
                board[row][i].setTileChar(guess.charAt(i));
                board[row][i].setTileColor(Color.green);
                gameOver = true;
            }
        }
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == hiddenWord.charAt(i)) {
                board[row][i].setTileChar(guess.charAt(i));
                board[row][i].setTileColor(new Color(108, 169, 101));
            } else if (hiddenWord.contains(guess.substring(i, i + 1))) {
                board[row][i].setTileChar(guess.charAt(i));
                board[row][i].setTileColor(new Color(200, 182, 83));
            } else {
                board[row][i].setTileChar(guess.charAt(i));
                board[row][i].setTileColor(new Color(185, 95, 120));

            }
        }
        row++;
    }

    public void printGameState() {
        for (Tile[] chars : board) {
            for (Tile aChar : chars) {
                System.out.println(aChar);
            }
        }
    }
    public void updateBoard(String guess) {
        if (isValid(guess)) {
            for (int i = 0; i < guess.length(); i++) {
                board[row][i].setTileChar(guess.charAt(i));
            }
        }
    }

    public void setHiddenWord(String word) {
        hiddenWord = word;
    }

    public void resetBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j].resetTile();
            }
        }
        words = wordScanner();
        hiddenWord = getRandomWord();
        row = 0;
        gameOver = false;
        usedGuesses = new ArrayList<>();
    }


    public void gameOver() {
        if (hiddenWord.equals(String.valueOf(wordChars))) {
            JOptionPane.showMessageDialog(null, "You Win!");
        } else if (row >= 5) {
            JOptionPane.showMessageDialog(null, "You Lose");
        }
    }

    public Tile getCell(int c, int r) {
        return board[r][c];
    }

    public String getHiddenWord() {
        return hiddenWord;
    }
    public void  printHiddenWord() {
        System.out.println(getHiddenWord());
    }
    public String getWordChars() {
        StringBuilder str = new StringBuilder();
        for (char c : wordChars) {
            str.append(c);
        }
        return String.valueOf(str);
    }
    public int getRow() {
        return row;
    }
    public int getColumn() {
        return column;
    }

    public boolean getWon() {
        return gameOver;
    }
    public String [] getWords() {
        return words;
    }
    public Tile [][] getBoard() {
        return board;
    }
    public void incRow() {
        row++;
    }
    public Tile[] getRowTiles() {
        Tile[] newBoard = new Tile[board[0].length];
        for (int i = 0; i < board[0].length; i++) {
            newBoard[i] = (board[0][i]);
        }
        return newBoard;
    }

    public static void main(String[] args) {

        Wordle wordle = new Wordle();

        wordle.printHiddenWord();
    }

    public void setColumn(int i) {
        column = i;
    }

}