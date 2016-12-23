package sudoku;

public class Cell {

  private int row, col;
  private int value;

  
  /*
   * Constructor
   */
  public Cell(int row, int col, int value) {
    this.row = row;
    this.col = col;
    
    this.value = value;
  }
  

  /*
   * Accessors
   */
  public int getRow() {
    return row;
  }

  public int getColumn() {
    return col;
  }
  
  public int getValue() {
    return value;
  }
  
  public void setValue(int value) {
    assert(value >= 0 && value <= 9) : "Invalid value";
    
    this.value = value;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + row;
    result = prime * result + col;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (obj == null) {
      return false;
    }

    if (getClass() != obj.getClass()) {
      return false;
    }

    Cell other = (Cell) obj;
    if (row != other.row || col != other.col) {
      return false;
    }

    return true;
  }
  
}
