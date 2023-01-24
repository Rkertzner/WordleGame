package org.cis1200.wordle;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;


/**
 * This class instantiates a TicTacToe object, which is the model for the game.
 * As the user clicks the game board, the model is updated. Whenever the model
 * is updated, the game board repaints itself and updates its status JLabel to
 * reflect the current state of the model.
 *
 * This game adheres to a Model-View-Controller design framework. This
 * framework is very effective for turn-based games. We STRONGLY
 * recommend you review these lecture slides, starting at slide 8,
 * for more details on Model-View-Controller:
 * https://www.seas.upenn.edu/~cis120/current/files/slides/lec37.pdf
 *
 * In a Model-View-Controller framework, GameBoard stores the model as a field
 * and acts as both the controller (with a MouseListener) and the view (with
 * its paintComponent method and the status JLabel).
 */
public class WordleGameBoard extends JPanel {

    private final Wordle wordle; // model for the game
    private JFrame status; // current status text

    private Tile[][] tileGrid;
    private String guess;


    // Game constants
    public static final int BOARD_WIDTH = 500;
    public static final int BOARD_HEIGHT = 600;

    /**
     * Initializes the game board.
     */
    public WordleGameBoard(JFrame statusInit) {
        // creates border around the court area, JComponent method

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Enable keyboard focus on the court area. When this component has the
        // keyboard focus, key events are handled by its key listener.
        setFocusable(true);
        status = statusInit;
        wordle = new Wordle();
        tileGrid = wordle.getBoard();
        // initializes model for the game
    }


    /**
     * (Re-)sets the game to its initial state.
     */
    public void reset() {
        wordle.resetBoard();
        repaint();
        JOptionPane.showMessageDialog(
                null,
                "Guess the Wordle in six tries. \n" +
                        "Each guess must be a five-letter word.\n" +
                        "The color of the tiles will change to show " +
                        "how close your guess was to the word.");
        System.out.println(wordle.getHiddenWord());

        while (wordle.getRow() < 6) {

            String output = JOptionPane.showInputDialog(null, "Enter guess:");
            while (!wordle.isValid(output)) {
                output = JOptionPane.showInputDialog(null,
                        "Guess not valid. please enter a real 5-letter word:");
            }
            wordle.playTurn(output);
            if (wordle.getWon()) {
                JOptionPane.showMessageDialog(null, "You Win!");
                break;
            }
            requestFocusInWindow();
            repaint();
        }
        if (wordle.getRow() > 5 && !wordle.getWon()) {
            JOptionPane.showMessageDialog(null, "You Lose!");
        }
        repaint();
        // Makes sure this component has keyboard/mouse focus
    }

    /**
     * Draws the game board.
     * There are many ways to draw a game board. This approach
     * will not be sufficient for most games, because it is not
     * modular. All of the logic for drawing the game board is
     * in this method, and it does not take advantage of helper
     * methods. Consider breaking up your paintComponent logic
     * into multiple methods or classes, like Mushroom of Doom.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        this.setLayout(new GridLayout(6, 5, 5, 5));

        this.setPreferredSize(new Dimension(WIDTH - 150, 400));
        this.setMaximumSize(new Dimension(WIDTH - 150, 400));

        // Draws board grid

        g.drawLine(100, 0, 100, 600);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(400, 0, 400, 600);
        g.drawLine(0, 100, 500, 100);
        g.drawLine(0, 200, 500, 200);
        g.drawLine(0, 300, 500, 300);
        g.drawLine(0, 400, 500, 400);
        g.drawLine(0, 500, 500, 500);

        Tile[][] board = wordle.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                this.add(board[i][j]);
            }
        }
    }

    /**
     * Returns the size of the game board.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }


}