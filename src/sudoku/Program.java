package sudoku;

import java.util.Scanner;

public class Program {
  
  public static void main(String[] args) {    
    if (args.length == 0) {
      solveInputSudoku();
    }
    
    switch (args[0]) {
    
      case "-help":
      case "-h":
        System.out.println(printHelp());
        break;
        
      case "-generate":
      case "-g":
        Sudoku sudoku = Sudoku.generate();
        System.out.println(sudoku.toString());
        break;
        
      case "-solve":
      case "-s":
        solveInputSudoku();
        break;
        
      default:
        System.out.println(printHelp());
        break;
    }
  }
  
  
  private static void solveInputSudoku() {
    // Read in sudoku from console
    Scanner in = new Scanner(System.in);
    
    String[] sudoku = new String[Configuration.sudokuSize];
    for (int i = 0; i < Configuration.sudokuSize; i++) {
      String line = in.nextLine();
      if (line.length() != Configuration.sudokuSize) {
        System.err.println(String.format("Each line needs to be {0} chars in length.", Configuration.sudokuSize));
        System.exit(-1);
      }
      
      sudoku[i] = line;
    }
    
    // Cleanup of resources
    in.close();
    
    // Parse and solve
    Sudoku sud = Sudoku.parse(sudoku);
    
    SudokuSolver solver = new SudokuSolver();
    solver.solve(sud);
    
    System.out.println();
    System.out.println("Solved Sudoku:");
    System.out.println(sud.toString());
  }
  
  private static String printHelp() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("Usage:");
    sb.append("\n");
    
    sb.append("-help, -h");
    sb.append("\tPrints help, list of commands, etc.");
    sb.append("\n");
    
    sb.append("-generate, -g");
    sb.append("\tGenerates a random sudoku.");
    sb.append("\n");
    
    sb.append("-solve, -s");
    sb.append("\tSolves input sudoku.");
    sb.append("\n");
    
    return sb.toString();
  }
}