package sudoku;

public class Program {
  public static void main(String[] args) {
    // Sudoku solving demonstration

    // Random "Evil" difficulty sudoku from websudoku.com
    int[][] sudoku = { 
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

    Sudoku evilSudoku = new Sudoku(sudoku);
    evilSudoku.solve();

    if (evilSudoku.isSolved()) {
      System.out.println("Solved Evil sudoku\n");
      evilSudoku.print();
      System.out.println("\n\n-----------\n\n");
    }

    // Sudoku generation demonstration
    System.out.println("\nGenerated puzzles\n");

    for (int i = 0; i < 5; i++) {
      Sudoku s = Sudoku.generate();
      s.print();
      System.out.println("-----------");
    }
  }
}