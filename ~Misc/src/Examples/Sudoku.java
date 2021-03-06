/**
 * 
 */
package Examples;

/**
 * @author pajensen
 *
 */
public class Sudoku
{
   // How do we represent a puzzle with variables?
    
   private int[][] puzzle;  // zero represents empty cell
    
   // What functionality do we want?
   
   public Sudoku ()
   {
       puzzle = new int[9][9];
   }
   
   // Mutator
   
   public void setLocation (int row, int col, int value)
   {
       puzzle[row][col] = value;
   }
   
   public String toString ()
   {
       String result = "";
       for (int r = 0; r < 9; r++)
       {
           for (int c = 0; c < 9; c++)
           {
               result += puzzle[r][c] + " ";
               if (c == 2 || c == 5)
                   result += " ";
           }
           result += "\n";
           if (r == 2 || r == 5)
               result += "\n";
       }
       return result;
   }
}
