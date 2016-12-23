package main;

import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.Test;

import sudoku.Sudoku;


public class SudokuGenerationTests {
  
  private Logger logger = Logger.getLogger(SudokuGenerationTests.class.getSimpleName());
  

  /*
   * Tests
   */
  @Test
  public void generateSudoku() {
    Sudoku sudoku = Sudoku.generate();
    
    int zeroCount = 0;
    int nonZeroCount = 0;
    
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        if (sudoku.getCellValue(i, j) == 0) {
          zeroCount++;
        } else {
          nonZeroCount++;
        }
      }
    }
    
    assertTrue(zeroCount > 0 && zeroCount < 81);
    assertTrue(nonZeroCount > 0);
    
    logger.fine(sudoku.toString());
  }

}
