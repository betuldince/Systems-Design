// Name:Betul Dincer
// USC NetID:1816821532
// CS 455 PA3
// Fall 2023
/**
   Tester for VisibleField more comparasive
*/
public class VisibleFieldTester2 {
    public static void main(String[] args) {
        // Create a test minefield
        boolean[][] mineData = {
            {false, true, false, false},
            {true, false, false, true},
            {false, false, true, false}
        };
        MineField mineField = new MineField(mineData);
        VisibleField visibleField = new VisibleField(mineField);
        // testNumMinesLeft(visibleField, mineField);
        // Test the initial state
        testInitialState(visibleField, mineField);

        // Test resetting the game display
        testResetGameDisplay(visibleField, mineField);
              testIsGameOver(visibleField, mineField);

        // Test uncovering a square
        testUncover(visibleField, mineField);

        // Test guessing squares
        testCycleGuess(visibleField, mineField);

        // Additional tests
        
       
       
       testResetGameDisplay(visibleField, mineField);
        testIsUncovered(visibleField, mineField);
       testResetGameDisplay(visibleField, mineField);
       testUncover(visibleField, mineField);
    }

    private static void testInitialState(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing initial state:");
        // Check the initial state of various squares
        assert visibleField.getStatus(0, 0) == VisibleField.COVERED : "Square (0, 0) should be covered initially";
        assert visibleField.getStatus(1, 1) == VisibleField.COVERED : "Square (1, 1) should be covered initially";
        assert visibleField.getStatus(2, 2) == VisibleField.COVERED : "Square (2, 2) should be covered initially";

        // Check the number of mines left initially
        assert visibleField.numMinesLeft() == mineField.numMines() : "Number of mines left mismatch initially";
    }
   

    private static void testResetGameDisplay(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing resetGameDisplay:");
        // Uncover some squares
        visibleField.uncover(0, 0);
        visibleField.uncover(2, 0);
        visibleField.uncover(2, 1);

        // Reset the game display
        visibleField.resetGameDisplay();
        // Check the state of the squares after reset
        assert visibleField.getStatus(0, 0) == VisibleField.COVERED : "Square (0, 0) should be covered after reset";
        assert visibleField.getStatus(2, 0) == VisibleField.COVERED : "Square (2, 0) should be covered after reset";
        assert visibleField.getStatus(2, 1) == VisibleField.COVERED : "Square (2, 1) should be covered after reset";

        // Check the number of mines left after reset
        assert visibleField.numMinesLeft() == mineField.numMines() : "Number of mines left should match mineField after reset";
    }

    private static void testUncover(VisibleField visibleField, MineField mineField) {
       
        System.out.println("Testing uncover:");
        // Uncover a square that contains a mine
        int uncoveredRow = 0;
        int uncoveredCol = 1;
        boolean resultUncovered = visibleField.uncover(uncoveredRow, uncoveredCol);
        assert !resultUncovered : "Uncovering a mine should return false";
        assert visibleField.getStatus(uncoveredRow, uncoveredCol) == VisibleField.EXPLODED_MINE : "Uncovering a mine should result in EXPLODED_MINE status";
       
                System.out.println(visibleField.getStatus(2, 0));


        // Uncover some empty squares
        visibleField.uncover(0, 0);
        visibleField.uncover(2, 0);

        // Check the state of squares after uncovering
        assert visibleField.getStatus(0, 0) == mineField.numAdjacentMines(0, 0) : "Uncovered square should show adjacent mine count";
       
        System.out.println(visibleField.getStatus(2, 0) + " " + mineField.numAdjacentMines(2, 0));
       
        assert visibleField.getStatus(2, 0) == mineField.numAdjacentMines(2, 0) : "Uncovered square should show adjacent mine count";
    }

    private static void testCycleGuess(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing cycleGuess:");
        // Cycle guess state of a square
        int guessRow = 2;
        int guessCol = 1;
        visibleField.cycleGuess(guessRow, guessCol);
        assert visibleField.getStatus(guessRow, guessCol) == VisibleField.MINE_GUESS : "MINE_GUESS expected";
    }

    private static void testNumMinesLeft(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing numMinesLeft:" + visibleField.numMinesLeft() + mineField.numMines());
        // Check the number of mines left initially
       
        assert visibleField.numMinesLeft() == mineField.numMines() : "Number of mines left mismatch initially";

        // Uncover some squares
        visibleField.uncover(0, 0);
        visibleField.uncover(2, 0);
        visibleField.uncover(2, 1);

        // Check the number of mines left after uncovering
       System.out.println("Testing numMinesLeft:" + visibleField.numMinesLeft() + mineField.numMines());

        assert visibleField.numMinesLeft() == mineField.numMines() - 3 : "Number of mines left mismatch after uncovering";
        // Guess a square
        visibleField.cycleGuess(1, 1);

        // Check the number of mines left after guessing
        assert visibleField.numMinesLeft() == mineField.numMines() - 2 : "Number of mines left mismatch after guessing";
    }

    private static void testIsGameOver(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing isGameOver:"+visibleField.isGameOver() );
        // Game should not be over initially
        
        assert !visibleField.isGameOver() : "Game should not be over initially";

        // Uncover all non-mine squares
        visibleField.uncover(0, 0);
        visibleField.uncover(0, 2);
       visibleField.uncover(0, 3);
        visibleField.uncover(1, 1);
       visibleField.uncover(1, 2);
       visibleField.uncover(2, 0);
       visibleField.uncover(2, 1);
       visibleField.uncover(2, 3);

       
        // Game should be over (win) when all non-mine squares are uncovered
        assert visibleField.isGameOver() : "Game should be over after winning";

        // Reset the game display
        visibleField.resetGameDisplay();

        // Game should not be over after reset
        assert !visibleField.isGameOver() : "Game should not be over after reset";

        // Uncover a mine (game over - lose)
        visibleField.uncover(0, 1);

        // Game should be over (lose) when a mine is uncovered
        assert visibleField.isGameOver() : "Game should be over after losing";
    }

    private static void testIsUncovered(VisibleField visibleField, MineField mineField) {
        System.out.println("Testing isUncovered:");
        // Test isUncovered for various squares
        assert !visibleField.isUncovered(0, 0) : "Square (0, 0) should not be uncovered initially";
        assert !visibleField.isUncovered(1, 1) : "Square (1, 1) should not be uncovered initially";
        assert !visibleField.isUncovered(2, 2) : "Square (2, 2) should not be uncovered initially";

        // Uncover some squares
        visibleField.uncover(0, 0);
        visibleField.uncover(2, 0);
        visibleField.uncover(2, 1);

        // Test isUncovered after uncovering
        assert visibleField.isUncovered(0, 0) : "Square (0, 0) should be uncovered";
        assert visibleField.isUncovered(2, 0) : "Square (2, 0) should be uncovered";
        assert visibleField.isUncovered(2, 1) : "Square (2, 1) should be uncovered";

        // Guess a square
        visibleField.cycleGuess(1, 1);

        // Test isUncovered after guessing
        assert !visibleField.isUncovered(1, 1) : "Square (1, 1) should not be uncovered after guessing";
    }
}
