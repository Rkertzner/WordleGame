package org.cis1200.wordle;
import java.awt.*;
import javax.swing.*;
import java.awt.Color;



public class Tile extends JLabel {
    private char c;
    private Color backgroundColor;


    public Tile() {
        super();
        super.setOpaque(true);
        super.setPreferredSize(new Dimension(100, 100));
        char d = Character.toUpperCase(c);
        super.setText(String.valueOf(d));
        super.setFont(new Font("Helvetica Neue ", Font.PLAIN, 48));
        super.setBackground(new Color(120, 124, 127));
        super.setForeground(Color.white);
        super.setVerticalAlignment(SwingConstants.CENTER);
        super.setHorizontalAlignment(SwingConstants.CENTER);
    }


    public void setTileColor(Color newColor) {
        super.setBackground(newColor);
    }
    public void resetTile() {
        super.setBackground(Color.lightGray);
        super.setText(String.valueOf(c));
    }
    public void setTileChar(char newC) {
        Character.toUpperCase(newC);
        super.setText(String.valueOf(newC));
    }

    public char getChar() {
        return c;
    }


}
