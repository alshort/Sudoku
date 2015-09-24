package sudoku;

public class Program
{
  public static void main(String[] args)
  {
    System.out.println("\nGenerated puzzles\n");
    
    for (int i = 0; i < 5; i++)
    {
      Sudoku s = Sudoku.generate();
      s.print();
      System.out.println("-----------");
    }
  }
}
