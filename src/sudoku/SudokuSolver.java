package sudoku;

public class SudokuSolver {
  
  private final static int SIZE = Configuration.sudokuSize;
  private final static int UNASSIGNED = 0;
  
  
  /*
   * Constructor
   */
  public SudokuSolver() { }
  
  
  /*
   * Methods
   */
  public boolean solve(Sudoku sudoku) {
    return solveSub(sudoku);
  }

  private boolean solveSub(Sudoku sudoku) {

    // Find a random, unassignedcell
    Cell unassignedCell = new Cell(-1, -1, -1);
    Cell cell = findUnassignedLocation(sudoku);

    // If there isn't an unassigned cell, the sudoku is solved
    if (cell.equals(unassignedCell)) {
      return true;
    } else {
      unassignedCell = cell;
    }

    // Grab the coordinates
    int row = unassignedCell.getRow();
    int col = unassignedCell.getColumn();

    // Test each potential value in turn
    for (int val = 1; val <= SIZE; val++) {
      if (isSafe(sudoku, val, row, col)) {
        sudoku.setCellValue(row, col, val);

        // Recurse to see if value can be successfully placed
        if (solveSub(sudoku)) {
          return true;
        }

        // If unsuccessful, mark as unassigned and try next value
        sudoku.setCellValue(row, col, UNASSIGNED);
      }
    }

    // Could not solve given sudoku
    return false;
  }
  
  private static Cell findUnassignedLocation(Sudoku sudoku) {
    // Returns *first* unassigned cell, going from L-to-R, up-to-down
    for (int i = 0; i < SIZE; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (sudoku.getCellValue(i, j) == UNASSIGNED) {
          return new Cell(i, j, UNASSIGNED);
        }
      }
    }

    return new Cell(-1, -1, -1);
  }
  
  private static boolean isSafe(Sudoku sudoku, int num, int row, int col) {
    return (!isNumInCell(sudoku, num, row, col) && !isNumInRow(sudoku, num, row) && !isNumInColumn(sudoku, num, col));
  }
  
  private static boolean isNumInCell(Sudoku sudoku, int num, int row, int col) {
    // Get the box the number is in (1 box = 9 cells)
    int boxX = col - col % 3;
    int boxY = row - row % 3;

    // Go through each cell in the box to check the value isn't there already
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (sudoku.getCellValue(boxY + i, boxX + j) == num) {
          return true;
        }
      }
    }

    return false;
  }

  private static boolean isNumInRow(Sudoku sudoku, int num, int row) {
    for (int i = 0; i < SIZE; i++) { // Requires square sudoku
      if (sudoku.getCellValue(row, i) == num) {
        return true;
      }
    }

    return false;
  }

  private static boolean isNumInColumn(Sudoku sudoku, int num, int col) {
    for (int i = 0; i < SIZE; i++) {
      if (sudoku.getCellValue(i, col) == num) {
        return true;
      }
    }

    return false;
  }
  
  
  
  
}
