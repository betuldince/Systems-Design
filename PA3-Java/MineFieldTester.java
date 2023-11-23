// Name:Betul Dincer
// USC NetID:1816821532
// CS 455 PA3
// Fall 2023

/**
   Tester for MineField
*/

public class MineFieldTester {

    public static void main(String[] args) {
        // Test case 1: Create a minefield from a hard-coded array
        boolean[][] mineData1 = {
            {false, true, false, false},
            {true, false, false, true},
            {false, false, true, false},
            {true, false, true, false}
        };
        MineField mineField1 = new MineField(mineData1);
        testMineField(mineField1, 4, 4, 6);
        System.out.println("numAdjacentMines "+mineField1.numAdjacentMines(2,1));
        System.out.println("hasMine "+ mineField1.hasMine(2,2));
        System.out.println(mineField1.toString());
        System.out.println("inRange "+ mineField1.inRange(4,1));
        System.out.println("reset ");
        mineField1.resetEmpty();
        System.out.println("after reset "+ mineField1.hasMine(2,2));
        System.out.println(mineField1.toString());
        mineField1.populateMineField(2,1);
        System.out.println("After populate ");
        System.out.println(mineField1.toString());
        //System.out.println(mineField1[2][2]);

        // Test case 2: Create an empty minefield and populate it
        int numRows = 5;
        int numCols = 5;
        int numMines = 8;
        MineField mineField2 = new MineField(numRows, numCols, numMines);
        mineField2.populateMineField(0, 0);
        testMineField(mineField2, numRows, numCols, numMines);
        System.out.println(mineField2.toString());
        // Additional test cases can be added here
    }

    public static void testMineField(MineField mineField, int expectedRows, int expectedCols, int expectedMines) {
        // Test numRows and numCols
        int actualRows = mineField.numRows();
        int actualCols = mineField.numCols();
        if (actualRows == expectedRows && actualCols == expectedCols) {
            System.out.println("Test Passed: numRows and numCols are as expected.");
        } else {
            System.out.println("Test Failed: numRows or numCols are not as expected.");
        }

        // Test numMines
        int actualMines = mineField.numMines();
        if (actualMines == expectedMines) {
            System.out.println("Test Passed: numMines is as expected.");
        } else {
            System.out.println("Test Failed: numMines is not as expected.");
        }

         
    }
}
