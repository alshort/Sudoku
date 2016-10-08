package main;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sudoku.Sudoku;


public class SudokuParsingTests {
  
  private Logger logger = Logger.getLogger(SudokuParsingTests.class.getSimpleName());
  
  
  /*
   * Test Data
   */
  private final int[][] testSudokuInts = { 
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
  
  private String[] testSudokuStrs = new String[testSudokuInts.length];
  

  /*
   * JUnit Behaviour
   */
  @Before
  public void setUp() throws Exception {
    int i = 0;
    for (int[] row : testSudokuInts) {
      testSudokuStrs[i] = joinIntArray(row);
      logger.fine(i + ": " + testSudokuStrs[i]);
      i++;
    }
  }

  @After
  public void tearDown() throws Exception {
  }
  

  /*
   * Tests
   */
  @Test
  public void canParseIntArray() {
    Sudoku sudoku = Sudoku.parse(testSudokuInts);
    
    for (int i = 0; i < testSudokuInts.length; i++) {
      for (int j = 0; j < testSudokuInts[0].length; j++) {
        assertEquals(testSudokuInts[i][j], sudoku.getCellValue(i, j));
      }
    }
  }
  
  @Test
  public void canParseStrArray() {
    Sudoku sudoku = Sudoku.parse(testSudokuStrs);
    
    for (int i = 0; i < testSudokuStrs.length; i++) {
      for (int j = 0; j < testSudokuStrs[0].length(); j++) {
        int value = Character.getNumericValue(testSudokuStrs[i].charAt(j));
        
        assertEquals(value, sudoku.getCellValue(i, j));
      }
    }
  }
  
  @Test
  public void toStringTest() {
    // Actual
    Sudoku sudoku = Sudoku.parse(testSudokuStrs);
    
    // Expected
    StringBuilder sb = new StringBuilder();
    
    for (String s : testSudokuStrs) {
      sb.append(s);
      if (!testSudokuStrs[testSudokuStrs.length - 1].equals(s)) {
        sb.append("\n");
      }
    }
    
    assertEquals(sb.toString(), sudoku.toString());
  }

  
  /*
   * Helper Methods
   */
  private String joinIntArray(int[] arr) {
    String s = "";
    for (int i : arr) {
      s += i;
    }
    return s;
  }
}