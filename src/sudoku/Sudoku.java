package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sudoku {
  
  private final static int SIZE = 9;
  private final static int UNASSIGNED = 0;
  
  
  private Cell[][] cells;

  
  /*
   * Constructor
   */
  private Sudoku(Cell[][] cells) {
    this.cells = cells;
  }
  
  
  /*
   * Accessors
   */
  public int getCellValue(int row, int col) {
    return cells[row][col].getValue();
  }
  
  public void setCellValue(int row, int col, int value) {
    cells[row][col].setValue(value);
  }
  
  
  public static Sudoku parse(int[][] sudoku) {
    assert(sudoku.length == SIZE) : "Unsupported Sudoku size.";
    assert(sudoku.length == sudoku[0].length) : "Unsupported Sudoku size - square sudokus only.";
    
    Cell[][] cells = new Cell[sudoku.length][sudoku[0].length];
    
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku[0].length; j++) {
        cells[i][j] = new Cell(i, j, sudoku[i][j]);
      }
    }
    
    return new Sudoku(cells);
  }
  
  public static Sudoku parse(String[] sudoku) {
    assert(sudoku.length == SIZE) : "Unsupported Sudoku size.";
    assert(sudoku.length == sudoku[0].length()) : "Unsupported Sudoku size - square sudokus only.";
    
    Cell[][] cells = new Cell[sudoku.length][sudoku[0].length()];
    
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku[0].length(); j++) {
        int cellValue = Character.getNumericValue(sudoku[i].charAt(j));
        cells[i][j] = new Cell(i, j, cellValue);
      }
    }
    
    return new Sudoku(cells);
  }
  
  /**
   * Generated a standard 9x9 sudoku.
   * @return Sudoku object populated with 
   */
  public static Sudoku generate() {
    Cell[][] cells = new Cell[SIZE][SIZE];
    
    // Initialise the array with empty objects, zero'd sudoku
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[0].length; j++) {
        cells[i][j] = new Cell(i, j, 0);
      }
    }
    
    generateSub(cells);
    
    // Black out random cells
    Random random = new Random();
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[0].length; j++) {
        if (random.nextFloat() < 0.4f) {
          cells[i][j].setValue(0);
        }
      }
    }
    
    return new Sudoku(cells);
  }
  
  private static boolean generateSub(Cell[][] sudoku) {
    Cell unassignedCell = new Cell(-1, -1, -1);
    Cell cell = findUnassignedLocation(sudoku);

    //
    if (cell.equals(unassignedCell)) {
      return true;
    } else {
      unassignedCell = cell;
    }

    // Populate a list of options
    List<Integer> values = new ArrayList<Integer>();
    for (int i = 1; i <= SIZE; i++) {
      values.add(i);
    }

    // Randomly shuffle list
    long seed = System.nanoTime();
    Collections.shuffle(values, new Random(seed));

    int row = unassignedCell.getRow();
    int col = unassignedCell.getColumn();

    for (int i = 1; i <= SIZE; i++) {
      int val = values.remove(0);

      if (isSafe(sudoku, val, row, col)) {
        sudoku[row][col].setValue(val);

        if (generateSub(sudoku)) {
          return true;
        }

        sudoku[row][col].setValue(UNASSIGNED);
      }
    }

    return false;
  }
  
  private static Cell findUnassignedLocation(Cell[][] cells) {
    // Returns *first* unassigned cell, going from L-to-R, up-to-down
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[i].length; j++) {
        if (cells[i][j].getValue() == UNASSIGNED) {
          return new Cell(i, j, UNASSIGNED);
        }
      }
    }

    return new Cell(-1, -1, -1);
  }
  
  private static boolean isSafe(Cell[][] sudoku, int num, int row, int col) {
    return (!isNumInCell(sudoku, num, row, col) && !isNumInRow(sudoku, num, row) && !isNumInColumn(sudoku, num, col));
  }
  
  private static boolean isNumInCell(Cell[][] sudoku, int num, int row, int col) {
    // Get the box the number is in (1 box = 9 cells)
    int boxX = col - col % 3;
    int boxY = row - row % 3;

    // Go through each cell in the box to check the value isn't there already
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (sudoku[boxY + i][boxX + j].getValue() == num) {
          return true;
        }
      }
    }

    return false;
  }

  private static boolean isNumInRow(Cell[][] sudoku, int num, int row) {
    for (int i = 0; i < sudoku[row].length; i++) {
      if (sudoku[row][i].getValue() == num) {
        return true;
      }
    }

    return false;
  }

  private static boolean isNumInColumn(Cell[][] sudoku, int num, int col) {
    for (int i = 0; i < sudoku.length; i++) {
      if (sudoku[i][col].getValue() == num) {
        return true;
      }
    }

    return false;
  }
  
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < cells.length; i++) {
      for (int j = 0; j < cells[0].length; j++) {
        sb.append(cells[i][j].getValue());
      }
      
      if (i < cells.length - 1) {
        sb.append("\n");
      }
    }
    
    return sb.toString();
  }
  
}
