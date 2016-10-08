package sudoku;

public class Sudoku2 {
  
  private Cell2[][] cells;

  
  /*
   * Constructor
   */
  private Sudoku2(Cell2[][] cells) {
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
  
  
  public Sudoku2 parse(int[][] sudoku) {
    assert(sudoku.length == 9) : "Unsupported Sudoku size.";
    assert(sudoku.length == sudoku[0].length) : "Unsupported Sudoku size - square sudokus only.";
    
    Cell2[][] cells = new Cell2[sudoku.length][sudoku[0].length];
    
    for (int i = 0; i < sudoku.length; i++) {
      for (int j = 0; j < sudoku[0].length; i++) {
        cells[i][j] = new Cell2(i, j, sudoku[i][j]);
      }
    }
    
    return new Sudoku2(cells);
  }
  
  public Sudoku2 parse(String[] sudoku) {
    assert(sudoku.length == 9) : "Unsupported Sudoku size.";
    return null;
  }
  
}
