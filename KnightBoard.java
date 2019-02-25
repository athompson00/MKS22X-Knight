public class KnightBoard{
  private int[][] board;
  private int numKnights = 0;
  public KnightBoard(int rows, int cols){
    if (rows <= 0 || cols <= 0){
      throw new IllegalArgumentException("rows and cols must be greater than 0");
    }
    board = new int[rows][cols];
    for (int r = 0; r < board.length; r++){
      for (int c = 0; c < board[0].length; c++){
        board[r][c] = 0;
      }
    }
  }

  public String toString(){
    String result = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[0].length; j++){
        if (board[i][j] % 10 == board[i][j]){
          result += "  " + board[i][j];
        } else {
          result += " " + board[i][j];
        }
      }
      result += "/n";
    }
    return result;
  }

  public boolean solve(int startingRow, int startingCol){
    if (startingRow < 0 || startingRow >= board.length || startingCol < 0 || startingCol >= board[0].length){
      throw new IllegalArgumentException("parameters must be greater than zero and within board boundaries");
    }
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[0].length; j++){
        if (board[i][j] != 0){
          throw new IllegalArgumentException("board must consist of all zeros");
        }
      }
    }
    return solveHelper(startingRow, startingCol);
  }

  public boolean solveHelper(int row, int col){
    if (row > board.length || col > board[0].length){
      return false;
    }
    board[row][col] = numKnights;
    numKnights++;
    return solveHelper(row + 2, col + 1) || solveHelper(row - 2, col + 1) || solveHelper(row + 2, col - 1) || solveHelper(row - 2, col - 1)
      || solveHelper(row + 1, col + 2) || solveHelper(row - 1, col + 2) || solveHelper(row + 1, col - 2) || solveHelper(row - 1, col - 2);
  }

  public int countSolutions(int startingRow, int startingCol){
    int result = 0;
    return result;
  }
}
