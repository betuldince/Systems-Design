// Name:Betul Dincer
// USC NetID:1816821532
// CS 455 PA3
// Fall 2023

/**
   Tester for VisibleField
*/
import java.util.Arrays;

public class VisibleFieldTester {
    public static void main(String[] args) {
        // Create a test minefield
        boolean[][] mineData = {
            {false, true, false, false},
            {true, false, false, true},
            {false, false, true, false}
        };
        MineField mineField = new MineField(mineData);
        VisibleField visibleField = new VisibleField(mineField);

        // Test the initial state
        testInitialState(visibleField, mineField);
        visibleField.uncover(0, 0);
        // Test resetting the game display
        testResetGameDisplay(visibleField, mineField);

        // Test uncovering a square
        testUncover(visibleField, mineField);

        // Test guessing squares
        testCycleGuess(visibleField, mineField);
    }

    private static void testInitialState(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing initial state:");
        // Test the number of mines left
        assert visibleField.numMinesLeft() == mineField.numMines() : "Number of mines left mismatch";

        // Test isGameOver
        assert !visibleField.isGameOver() : "Game should not be over initially";

        // Test getStatus for some squares
        assert visibleField.getStatus(0, 0) == VisibleField.COVERED : "Status mismatch for (0, 0)";
        assert visibleField.getStatus(1, 1) == VisibleField.COVERED : "Status mismatch for (1, 1)";
        assert visibleField.getStatus(2, 2) == VisibleField.COVERED : "Status mismatch for (2, 2)";

         
    }

    private static void testResetGameDisplay(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing resetGameDisplay:");
        // Call resetGameDisplay
        visibleField.resetGameDisplay();

        // Test the number of mines left
        assert visibleField.numMinesLeft() == mineField.numMines() : "Number of mines left mismatch after reset";

        // Test isGameOver
        assert !visibleField.isGameOver() : "Game should not be over after reset";

        // Test getStatus for some squares
        assert visibleField.getStatus(0, 0) == VisibleField.COVERED : "Status mismatch for (0, 0) after reset";
        assert visibleField.getStatus(1, 1) == VisibleField.COVERED : "Status mismatch for (1, 1) after reset";
        assert visibleField.getStatus(2, 2) == VisibleField.COVERED : "Status mismatch for (2, 2) after reset";
    }

    private static void testUncover(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing uncover:");

        // Choose a safe location to uncover (you may need to adjust this based on your mineData)
        int safeRow = 0;
        int safeCol = 0;

        // Choose a mine location to uncover (you may need to adjust this based on your mineData)
        int mineRow = 1;
        int mineCol = 0;

        // Uncover a safe square
        boolean resultSafe = visibleField.uncover(safeRow, safeCol);

        // Test if the game is over
        assert !visibleField.isGameOver() : "Game should not be over after uncovering a safe square";
        assert resultSafe : "Uncovering a safe square should return true";
        assert visibleField.getStatus(safeRow, safeCol) != VisibleField.COVERED : "Safe square should be uncovered";

        // Uncover a mine
        boolean resultMine = visibleField.uncover(mineRow, mineCol);

        // Test if the game is over
        assert visibleField.isGameOver() : "Game should be over after uncovering a mine";
        assert !resultMine : "Uncovering a mine should return false";
        assert visibleField.getStatus(mineRow, mineCol) == VisibleField.EXPLODED_MINE : "Uncovered mine should be EXPLODED_MINE";
    }

    private static void testCycleGuess(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing cycleGuess:");

        // Choose a location for guessing
        int guessRow = 2;
        int guessCol = 3;

        // Cycle through covered states (COVERED -> MINE_GUESS -> QUESTION -> COVERED)
        visibleField.cycleGuess(guessRow, guessCol);
        
         
        assert visibleField.getStatus(guessRow, guessCol) == VisibleField.MINE_GUESS : "MINE_GUESS expected";
       
       
        visibleField.cycleGuess(guessRow, guessCol);
        assert visibleField.getStatus(guessRow, guessCol) == VisibleField.QUESTION : "QUESTION expected";
        visibleField.cycleGuess(guessRow, guessCol);
        assert visibleField.getStatus(guessRow, guessCol) == VisibleField.COVERED : "COVERED expected";
    }
}
