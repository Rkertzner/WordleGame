=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: 41841110
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. 2D arrays: I implement a 2D array as the backbone of my game in the form of Tile[][], where a Tile is an
    implementation of a JLabel containing a character and background color dependent on the game logic.

  2. JUnit Testing: I implement JUnit tests to make sure the key game components of the Wordle.java file are
  functioning properly.

  3. Collections: I implement a TreeSet in the WordScanner method that reads the file containing 5-letter words into
  a private instance array in Wordle.java.

  4.File IO: I use a FileReader, which is part of Java's IO package, to read from the words.txt file and allows me to
  save it in a private instance array for easy access when constructing the game logic.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

    Tile.java: This file contains the Tiles that extend JLabel to make the game board display possible. The
    background color and character are updated to follow the game logic.

    Wordle.java: This file contains the game logic, or the backbone of the game. When certain conditions are met
    (game is not over, guess is valid) the game will playTurn with a certain String guess and will update the board
    (Tile[][]) and its Tile's characters and background colors according to the game logic.

    WordleGameBoard.java: This file contains the logic that allows me to repaint the board that's a part of the main
    JFrame found in the next file, RunWordle. Primarily, its reset function—which is provoked at the start of a game
    or when the Reset button is pressed—resets the 2D array of JLabels that compose the game board. It also contains
    a paintComponent in which the wordle.board (Tile[][]) is added to the grid layout of the paintComponent.

    RunWordle.java: This file simply contains the JFrame to which our components are added. Most importantly, to its
    center is added our WordleGameBoard, which updates the GUI as the game progresses or is restarted. This is the
    method that is then called in Game.java.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

    Certainly. It took quite a while to get the JLabels to print to the board, and I would likely have not been
    able to figure it out without TA help. Also, the separation of the Wordle.java and WordleGameBoard.java files
    was difficult, and there saw a lot of overlap between the two.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

    I would likely have refactored the 2D array from the Wordle.java class and made it of chars, and then adding
    them into JLabels in the paintComponent. Though printing the Tiles made for a large initial challenge, they
    made it quite easy to refactor the remaining GUI issues once the game logic was applied to the reset() method
    in WordleGameBoard. Nevertheless, implementing Tiles complicated GUI components.



========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
  //Source of words.txt:
    https://www-cs-faculty.stanford.edu/~knuth/sgb-words.txt
