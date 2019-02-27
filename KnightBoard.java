import java.util.*;

public class KnightBoard{
  private int[][] board;
  //private int[][] Optimize;
  private Spot[][] Optimize;
  public class Move{
    private int x;
    private int y;
    public Move(int x, int y){
      this.x = x;
      this.y = y;
    }
    public int getX(){
      return x;
    }
    public int getY(){
      return y;
    }
  }
  public class Spot{
    int x;
    int y;
    int possibleMoves;
    public Spot(int x, int y, int p){
      this.x = x;
      this.y = y;
      possibleMoves = p;
    }
    public void changeMoves(int i){
      possibleMoves += i;
    }
    public String toString(){
      return "" + possibleMoves;
    }
  }
  private ArrayList<Move> moves = new ArrayList<Move>();
  public static void main(String[] args){
    KnightBoard a = new KnightBoard(35, 35);
    //a.solve(0,0);
    //System.out.println(a.OpToString());
    System.out.println(a.solve(0,0));
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

    Move one = new Move(2, 1);
    moves.add(one);
    Move two = new Move(2, -1);
    moves.add(two);
    Move three = new Move(-2, 1);
    moves.add(three);
    Move four = new Move(-2, -1);
    moves.add(four);
    Move five = new Move(1, 2);
    moves.add(five);
    Move six = new Move(1, -2);
    moves.add(six);
    Move seven = new Move(-1, 2);
    moves.add(seven);
    Move eight = new Move(-1, -2);
    moves.add(eight);

    int possible = 0;

    Optimize = new Spot[rows][cols];
    for (int r = 0; r < rows; r++){
      for (int c = 0; c < cols; c++){
        possible = 0;
        for (int j = 0; j < 8; j++){
          if (((r + moves.get(j).getX()) >= 0) &&
             ((r + moves.get(j).getX()) < board.length) &&
             ((c + moves.get(j).getY()) >= 0) &&
             ((c + moves.get(j).getY()) < board.length)){
               possible++;
          }
        }
        Optimize[r][c] = new Spot(r, c, possible);
      }
    }
  }

  public String OpToString(){
    String result = "";
    for (int r = 0; r < Optimize.length; r++){
      for (int c = 0; c < Optimize[0].length; c++){
        result += Optimize[r][c] + " ";
      }
      result += "\n";
    }
    return result;
  }

  public String toString(){
    String result = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[0].length; j++){
        if (board[i][j] % 10 == board[i][j]){
          result += "   " + board[i][j];
        } else if (board[i][j] % 100 == board[i][j]){
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

  public void sort(ArrayList<Spot> n){
    for (int i = 1; i < n.size(); i++){
      Spot a = n.get(i);
      int b = i;
      while (b > 0 && a.possibleMoves < n.get(b - 1).possibleMoves){
        n.set(b, n.get(b - 1));
        n.set(b - 1, a);
        b--;
      }
    }
  }

  public boolean add(int r, int c, int KnightsAdded){
    if (!(board[r][c] == 0)){
      return false;
    }
    board[r][c] = KnightsAdded;
    for (int j = 0; j < 8; j++){
      if (((r + moves.get(j).getX()) >= 0) &&
         ((r + moves.get(j).getX()) < board.length) &&
         ((c + moves.get(j).getY()) >= 0) &&
         ((c + moves.get(j).getY()) < board.length)){
           Optimize[r + moves.get(j).getX()][c + moves.get(j).getY()].changeMoves(-1);
      }
    }
    return true;
  }

  public boolean remove(int r, int c){
    if (!(board[r][c] == 0)){
      return false;
    }
    board[r][c] = 0;
    for (int j = 0; j < 8; j++){
      if (((r + moves.get(j).getX()) >= 0) &&
         ((r + moves.get(j).getX()) < board.length) &&
         ((c + moves.get(j).getY()) >= 0) &&
         ((c + moves.get(j).getY()) < board.length)){
           Optimize[r + moves.get(j).getX()][c + moves.get(j).getY()].changeMoves(+1);
      }
    }
    return true;
  }

  public ArrayList<Spot> next(int r, int c){
    ArrayList<Spot> result = new ArrayList<Spot>();
      for (int j = 0; j < 8; j++){
        if (((r + moves.get(j).getX()) >= 0) &&
           ((r + moves.get(j).getX()) < board.length) &&
           ((c + moves.get(j).getY()) >= 0) &&
           ((c + moves.get(j).getY()) < board.length)){
             result.add(Optimize[r + moves.get(j).getX()][c + moves.get(j).getY()]);
        }
      }
      if (result.size() == 0){
        return result;
      }
      sort(result);
      return result;
  }

  public boolean solveHelper(int row, int col, int numKnights){

    if (row >= board.length || col >= board[0].length || row < 0 || col < 0){
      return false;
    }
    if (board[row][col] != 0){
      return false;
    }
    if (numKnights == board.length * board[0].length){
      add(row, col, numKnights);
      return true;
    }

    add(row, col, numKnights);
    ArrayList<Spot> newMoves = next(row, col);
    if (newMoves.size() == 0){
      return false;
    } else {
      for (int i = 0; i < newMoves.size(); i++){
        if (solveHelper(newMoves.get(i).x, newMoves.get(i).y, numKnights + 1)){
          return true;
        }
      }
      remove(row,col);
      System.out.println("nope");
      return false;
    }

    /*
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
    */

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
