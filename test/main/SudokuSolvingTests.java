package main;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Test;

import sudoku.Sudoku;
import sudoku.SudokuSolver;


public class SudokuSolvingTests {
  
  private Logger logger = Logger.getLogger(SudokuSolvingTests.class.getSimpleName());
  
  
  /*
   * Test Data
   */
  private final int[][] testSudoku = { 
      { 8, 0, 0, 0, 4, 7, 0, 0, 6 }, 
      { 0, 0, 1, 0, 0, 0, 0, 5, 0 }, 
      { 0, 0, 6, 8, 0, 0, 0, 0, 0 },
      { 0, 0, 4, 5, 0, 0, 0, 0, 9 }, 
      { 0, 8, 0, 0, 0, 0, 0, 7, 0 }, 
      { 6, 0, 0, 0, 0, 9, 5, 0, 0 },
      { 0, 0, 0, 0, 0, 1, 8, 0, 0 }, 
      { 0, 3, 0, 0, 0, 0, 4, 0, 0 }, 
      { 7, 0, 0, 6, 9, 0, 0, 0, 3 }
  };
  
  private String[] testSudokuSolved = {
      "853147926",
      "471962358",
      "296853741",
      "324576189",
      "985314672",
      "617289534",
      "562431897",
      "139728465",
      "748695213"
  };
  
  private SudokuSolver solver = new SudokuSolver();


  /*
   * Tests
   */
  @Test
  public void solveSudoku() {
    Sudoku sudokuToSolve = Sudoku.parse(testSudoku);
    Sudoku solvedSudoku = Sudoku.parse(testSudokuSolved);
    
    boolean canSolve = solver.solve(sudokuToSolve);
    
    assertTrue(canSolve);
    assertThat(sudokuToSolve.toString(), is(solvedSudoku.toString()));
    
    logger.fine(sudokuToSolve.toString());
  }

}
