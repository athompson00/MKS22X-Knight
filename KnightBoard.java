public class KnightBoard{
  private int[][] board;
  public static void main(String[] args){
    KnightBoard a = new KnightBoard(6, 6);
    System.out.println(a);
    System.out.println(a.countSolutions(0,0));
  //  System.out.println(a);
  }
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
      result += "\n";
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
    return solveHelper(startingRow, startingCol, 1);
  }

  public boolean solveHelper(int row, int col, int numKnights){
    if (row >= board.length || col >= board[0].length || row < 0 || col < 0){
      return false;
    }
    if (board[row][col] != 0){
      return false;
    }
    if (numKnights == board.length * board[0].length){
      board[row][col] = numKnights;
      return true;
    }
    for (int i = 0; i < 8; i++){
      board[row][col] = numKnights;
      if (i == 0){
        if (solveHelper(row + 2, col + 1, numKnights + 1)){
          return true;
        }
      }
      if (i == 1){
        if (solveHelper(row + 2, col - 1, numKnights + 1)){
          return true;
        }
      }
      if (i == 2){
        if (solveHelper(row - 2, col - 1, numKnights + 1)){
          return true;
        }
      }
      if (i == 3){
        if (solveHelper(row - 2, col + 1, numKnights + 1)){
          return true;
        }
      }
      if (i == 4){
        if (solveHelper(row + 1, col + 2, numKnights + 1)){
          return true;
        }
      }
      if (i == 5){
        if (solveHelper(row + 1, col - 2, numKnights + 1)){
          return true;
        }
      }
      if (i == 6){
        if (solveHelper(row - 1, col + 2, numKnights + 1)){
          return true;
        }
      }
      if (i == 7){
        if (solveHelper(row - 1, col - 2, numKnights + 1)){
          return true;
        }
      }
      board[row][col] = 0;
    }
    return false;
  }

  public int countSolutions(int startingRow, int startingCol){
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
    return countSolutionsHelper(startingRow, startingCol, 1);
  }

  public int countSolutionsHelper(int row, int col, int numKnights){
    int result = 0;
    if (row >= board.length || col >= board[0].length || row < 0 || col < 0){
      return 0;
    }
    if (board[row][col] != 0){
      return 0;
    }
    if (numKnights == board.length * board[0].length){
      return 1;
    }
    for (int i = 0; i < 8; i++){
      board[row][col] = numKnights;
      if (i == 0){
        result += countSolutionsHelper(row + 2, col + 1, numKnights + 1);
      }
      if (i == 1){
        result += countSolutionsHelper(row + 2, col - 1, numKnights + 1);
      }
      if (i == 2){
        result += countSolutionsHelper(row - 2, col - 1, numKnights + 1);
      }
      if (i == 3){
        result += countSolutionsHelper(row - 2, col + 1, numKnights + 1);
      }
      if (i == 4){
        result += countSolutionsHelper(row + 1, col + 2, numKnights + 1);
      }
      if (i == 5){
        result += countSolutionsHelper(row + 1, col - 2, numKnights + 1);
      }
      if (i == 6){
        result += countSolutionsHelper(row - 1, col + 2, numKnights + 1);
      }
      if (i == 7){
        result += countSolutionsHelper(row - 1, col - 2, numKnights + 1);
      }
      board[row][col] = 0;
    }
    return result;
  }
}
