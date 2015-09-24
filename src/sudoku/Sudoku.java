package sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Sudoku
{
  /*
   * Constants
   */
  private final static int SIZE = 9;
  private final static int UNASSIGNED = 0;
  
  /*
   * Variables
   */
  private int[][] sudoku;
  
  private int width;
  private int height;
  
  private boolean isSolved;
  
  /*
   * Constructors
   */  
  public Sudoku(int[][] sudoku)
  {
    assert(sudoku.length    == SIZE) : "Sudoku height is invalid (not equal to 9)";
    assert(sudoku[0].length == SIZE) : "Sudoku width is invalid (not equal to 9)";
    
    // Remember Java 2D arrays are arrays of arrays
    this.sudoku = sudoku;
    
    this.height = SIZE;
    this.width  = SIZE;
    
    this.isSolved = false;
  }
  
  
  /*
   * Methods
   */
  
  /**
   * Generates a sudoku object of standard 9x9 size.
   * Does not handle arbitrary sizes currently (e.g. no 6x6)
   * 
   * @return Fully filled Sudoku of size 9x9
   */
  public static Sudoku generate()
  {
    int[][] sudoku = new int[SIZE][SIZE];
    
    // Defer to a recursive sub-function
    // Sudoku passed by ref
    generateSub(sudoku);
    
    return new Sudoku(sudoku);
  }
  
  private static boolean generateSub(int[][] sudoku)
  {
    Cell unassignedCell = new Cell(-1, -1);    
    Cell cell = findUnassignedLocation(sudoku);
    
    // 
    if (cell.equals(unassignedCell))
      return true;
    else
      unassignedCell = cell;
    
    // Populate a list of options
    List<Integer> values = new ArrayList<Integer>();
    for (int i = 1; i <= SIZE; i++)
      values.add(i);
    
    // Randomly shuffle list
    long seed = System.nanoTime();
    Collections.shuffle(values, new Random(seed));
    
    int row = unassignedCell.getX();
    int col = unassignedCell.getY();
    
    for (int i = 1; i <= SIZE; i++)
    {
      int val = values.remove(0);
      
      if (isSafe(sudoku, val, row, col))
      {
        sudoku[row][col] = val;
        
        if (generateSub(sudoku))
          return true;
        
        sudoku[row][col] = UNASSIGNED;
      }
    }
    
    return false;
  }
  
  public boolean isSolved()
  {
    return isSolved;
  }
  
  /**
   * Solve the current sudoku (in place)
   * @return True if sudoku could be solved, false if not
   */
  public boolean solve()
  {
    return solveSub(sudoku);
  }
  
  /*
   * Recursive method to solve the sudoku
   */
  private boolean solveSub(int[][] sud)
  {
    // Find a random, unassignedcell
    Cell unassignedCell = new Cell(-1, -1);
    Cell cell = findUnassignedLocation(sud);
    
    // If there isn't an unassigned cell, the sudoku is solved
    if (cell.equals(unassignedCell))
      return (isSolved = true);
    else
      unassignedCell = cell;
    
    // Grab the coordinates
    int row = unassignedCell.getX();
    int col = unassignedCell.getY();
    
    // Test each potential value in turn
    for (int val = 1; val <= SIZE; val++)
    {
      if (isSafe(sud, val, row, col))
      {
        sud[row][col] = val;
        
        // Recurse to see if value can be successfully placed
        if (solveSub(sud))
          return (isSolved = true);
        
        // If unsuccessful, mark as unassigned and try next value
        sud[row][col] = UNASSIGNED;
      }
    }
    
    // Could not solve given sudoku
    return false;
  }
  
  private static Cell findUnassignedLocation(int[][] sud)
  {
    // Returns *first* unassigned cell, going from L-to-R, up-to-down
    for (int i = 0; i < sud.length; i++)
      for (int j = 0; j < sud[i].length; j++)
        if (sud[i][j] == UNASSIGNED)
          return new Cell(i, j);
    
    return new Cell(-1, -1);
  }
  
  private static boolean isSafe(int[][] sudoku, int num, int row, int col)
  {
    return (!isNumInCell(sudoku, num, row, col) && 
            !isNumInRow(sudoku, num, row) &&
            !isNumInColumn(sudoku, num, col));
  }
  
  private static boolean isNumInCell(int[][] sudoku, int num, int row, int col)
  {
    // Get the box the number is in (1 box = 9 cells)
    int boxX = col - col % 3;
    int boxY = row - row % 3;
    
    // Go through each cell in the box to check the value isn't there already
    for (int i = 0; i < 3; i++)
      for (int j = 0; j < 3; j++)
        if (sudoku[boxY + i][boxX + j] == num)
          return true;
    
    return false;
  }
  
  private static boolean isNumInRow(int[][] sudoku, int num, int row)
  {    
    for (int i = 0; i < sudoku[row].length; i++)
      if (sudoku[row][i] == num)
        return true;
    
    return false;
  }

  private static boolean isNumInColumn(int[][] sudoku, int num, int col)
  {
    for (int i = 0; i < sudoku.length; i++)
      if (sudoku[i][col] == num)
        return true;
    
    return false;
  }
  
  /**
   * Prints this sudoku to the System console.
   */
  public void print()
  {
    for (int i = 0; i < this.height; i++)
    {
      for (int j = 0; j < this.width; j++)
      {
        if (j > 0 && j % 3 == 0)
          System.out.print(" ");
        
        System.out.print(sudoku[i][j]);
      }
      
      if (i > 0 && i % 3 == 2)
        System.out.println();
      
      System.out.println();
    }
  }

}
