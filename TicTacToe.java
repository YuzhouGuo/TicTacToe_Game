//Student Name: Yuzhou Guo
//Student ID: 260715042

//This is a Tic Tac Toe game to play with the computer.
//Notice that this game can be bigger than three dimentions.

import java.util.Scanner;
import java.util.Random;
//Since we need to interact with the user and make random numbers,
//We import the Scanner class and Random class.

public class TicTacToe {

  public static void main (String args[]){
    
    play();
    //We call the play method from the main method.

  }
  
  public static void play (){
   
    int decider = 0;
    
    System.out.println("Please enter your name:");
    
    Scanner scan = new Scanner(System.in);
    
    String name = scan.nextLine();
    
    System.out.println("Welcome, " + name + "! Are you ready to play?");
    System.out.println("Please chose the dimention of your board:");
    
    while(!scan.hasNextInt()) {
      System.out.println("Please enter an integer.");
      scan.next();
    }
    
    int input = scan.nextInt();
    /* Here we use a while loop ahead so that in case the user doesn't give an integer, we can keep asking
     * for it; if we do reveive an integer, the program skip the while loop and store the integer in the 
     * variable "input".*/

    char[][] board = createBoard(input);
    
    Random r = new Random();
    int random = r.nextInt(2);
    
    if (random==1){
      System.out.println("The result of the coin toss is: 1");
      System.out.println("The AI has the first move.");
    }
    
    else if (random==0){
      System.out.println("The result of the coin toss is: 0");
      System.out.println("You have the first move.");
      System.out.println("Please put space between two numbers.");
      getUserMove(board);
      displayBoard (board);
    }
    
    int max = (int)(Math.pow(input,2));
    
    for (int n = 1; n<=(max-2); n+=2){
      /* Here, we need to make sure it goes smoothly from user's move -- displayBoard --(String)AI made its move--
       * getAIMove--displayBoard. Notice that this is a loop, so we need to be careful with how the bottom part of 
       * the loop connect with the top part.*/
      
      //We need to test often so that we don't miss any judgement.
      
      System.out.println("The AI made its move:");
      getAIMove(board);
      displayBoard (board);
      
      if(checkForWinner(board) == 'x'){
        System.out.println("GAME OVER!");
        System.out.println("YOU WIN!");
        decider++;
        return;
      }
      if(checkForWinner(board) == 'o'){
        System.out.println("GAME OVER!");
        System.out.println("YOU LOST!");
        decider++;
        return;
      }
      
      /* We put all the conditions in between the userMove and the AIMove so that we can check recetnly if there
       * is a winner already.*/
      
      getUserMove(board);
      displayBoard(board);
      
      if(checkForWinner(board) == 'x'){
        System.out.println("GAME OVER!");
        System.out.println("YOU WIN!");
        decider++;
        return;
      }
      
      if(checkForWinner(board) == 'o'){
        System.out.println("GAME OVER!");
        System.out.println("YOU LOST!");
        decider++;
        return;
      }
      
      if((random==0)&&(max%2 != 0)&&(n==(max-2))&&(checkForWinner(board) == ' ')){
        System.out.println("GAME OVER!");
        System.out.println("THERE IS NO WINNER THIS TIME!");
        decider++;
        return;
      }
    }
    
    if ((random==1)&&(decider==0)){
      System.out.println("The AI made its move:");
      getAIMove(board);    //if random=1, then the AI has one more chance.
      displayBoard (board); //decider here means there's no judgement made before.
      
      if(checkForWinner(board) == 'x'){
        System.out.println("GAME OVER!");
        System.out.println("YOU WIN!");
        return;
      }
      if(checkForWinner(board) == 'o'){
        System.out.println("GAME OVER!");
        System.out.println("YOU LOST!");
        return;
      }
      if((max%2 != 0)&&(checkForWinner(board) == ' ')){
        System.out.println("GAME OVER!");
        System.out.println("THERE IS NO WINNER THIS TIME!");
        return;
      }
    }
    
    if ((max%2 == 0)&&(decider==0)){
      if (random==1){
        getUserMove(board);    //if random=1, then the user should move first (The AI moves first last step).
        displayBoard (board); 
      
        if(checkForWinner(board) == 'x'){
          System.out.println("GAME OVER!");
          System.out.println("YOU WIN!");
          return;
        }
        if(checkForWinner(board) == 'o'){
          System.out.println("GAME OVER!");
          System.out.println("YOU LOST!");
          return;
        }
        if(checkForWinner(board) == ' '){
          System.out.println("GAME OVER!");
          System.out.println("THERE IS NO WINNER THIS TIME!");
          return;
        }
      }
      
      if (random==0){
        System.out.println("The AI made its move:");
        getAIMove(board);   
        displayBoard (board); 
      
        if(checkForWinner(board) == 'x'){
          System.out.println("GAME OVER!");
          System.out.println("YOU WIN!");
          return;
        }
        if(checkForWinner(board) == 'o'){
          System.out.println("GAME OVER!");
          System.out.println("YOU LOST!");
          return;
        }
        if(checkForWinner(board) == ' '){
          System.out.println("GAME OVER!");
          System.out.println("THERE IS NO WINNER THIS TIME!");
          return;
        }
      }
    }
   //We won't claim "there is no winner" until the last minute.
  }
  
  public static char[][] createBoard (int n){
  
    char[][] board = new char[n][n];
    
    for (int i = 0; i<n; i++){
      for (int j = 0; j<n; j++){
        board[i][j] = ' ';           //The board is created and all blank now.
      }
    }
    return board;
  }
  
  public static void displayBoard (char[][] empty){
  
    int high = (empty.length*2)+1;
    int length = (empty[0].length*2)+1;
    //So this is basically a larger size of the board we create before, just need to relocate each coordinate.
    //Make variables here so that it is clear to see instead of make next line super long.
    
    char[][] realBoard = new char[high][length];
    
    for (int i = 0; i<high; i++){
       if ((i)%2 == 0){
          printLine1(length);  //A helper method prints the "+-" line, called whenever the index of "high" is odd.
       }
       else{
       
       for (int j = 0; j<length; j++){
         if ((j)%2 == 0){
           System.out.print('|');
         }
         else{
           realBoard[i][j] = empty[(i-1)/2][(j-1)/2];
           System.out.print(realBoard[i][j]);
           //Here we go and find whether anywhere in the board we created before is not blank, and draw the symbol.
         }
       }
       System.out.println();
       }
    }
    
  }
  
  public static void printLine1 (int length){
    for (int i = 1; i<=length; i++){
      if (i%2 != 0){
        System.out.print('+');
      }
      else{
        System.out.print('-');
      }
    }
    System.out.println();
  }
  //A helper method prints the "+-" line.
  
  public static void writeOnBoard(char[][] board, char c, int x, int y){
    //A very important method in this program which is used over and over.
    /* We need to test the "notInBoard" first because we need to throw the illegalArgumentException 
     * before the computer does and crash the program.*/
    
    boolean notInBoard = (x>(board.length-1))||(y>(board[0].length-1));
    if(notInBoard){
      throw new IllegalArgumentException ("The position ("+ x +","+ y +") doesn't represent a cell on board.");
    }
        
    boolean charAlready = (board[x][y] != ' ');
    if(charAlready){
      throw new IllegalArgumentException("The position ("+ x +","+ y +") contains a character already.");
    }
    
    //If there's nothing wrong with the coordinate, then we write it on the board.
    board [x][y] = c;
  }
  
  public static void getUserMove (char[][] board){
    
    Scanner read = new Scanner(System.in);
    System.out.println("Please enter your move.");
    
    int x = read.nextInt();
    int y = read.nextInt();
    //By setting two "nextInt" methods, we can receive two numbers and store them separately from the user.
    
    for (int i = 1; i>0; i++){
      /* We need a big loop here so that we can keep asking new coordinates from the user, no matter the user 
       * gives the "charALready" mistake first or the "notInBoard" mistake first, we can connect everything 
       * smoothly instead of crash the program.*/
      
      boolean notInBoard = (x>(board.length-1))||(y>(board[0].length-1));
      
      while (notInBoard){
        System.out.println("The position ("+ x +","+ y +") doesn't represent a cell on board.");
        System.out.println("Please enter a new move.");
      
        x = read.nextInt();
        y = read.nextInt();
      
        notInBoard = (x>(board.length-1))||(y>(board[0].length-1));
      }
    
      boolean charAlready = (board[x][y] != ' ');
      
      if((!notInBoard)&&(!charAlready)){
        break;
      }
    
      while (charAlready){ // And we put this condition below since we don't have to compete with the computer
                           // who gives the illegalArgumentException first.
        System.out.println("The position ("+ x +","+ y +") contains a character already.");
        System.out.println("Please enter a new move.");
        
        x = read.nextInt();
        y = read.nextInt();
        
        notInBoard = (x>(board.length-1))||(y>(board[0].length-1));
        
        if (notInBoard){
          break;
        }
        charAlready = (board[x][y] != ' '); 
      }
    }
    writeOnBoard(board, 'x', x, y);  //If it skips the big loop, then write it on the board.
  }
  
  public static boolean checkForObviousMove (char[][] board){
    
    /* It is a necessary step here to store the true or false in a boolean variable. Because the methods we are 
     * going to see later changes the array, so it really helps get everything clear to store the booleans first 
     * instead of keeping calling them because mistakes may appear.*/
    
    boolean cObivous = column(board, 'o');
    boolean rObvious = row(board, 'o');
    boolean d1Obvious = diagonal1(board, 'o');
    boolean d2Obvious = diagonal2(board, 'o');
    
    boolean machineObvious = cObivous || rObvious || d1Obvious || d2Obvious;
    //Match any of them, the machine has an obvious move.
    
    boolean cAvoid = column(board, 'x');
    boolean rAvoid = row(board, 'x');
    boolean d1Avoid = diagonal1(board, 'x');
    boolean d2Avoid = diagonal2(board, 'x');
    //Use the same method here, but find "x" this time.
    /* Match any of them, the player has an obvious move, and the machine will choose to avoid it if it doesn't
     * match any of the "machineObvious".*/
    
    boolean avoidPlayer = cAvoid || rAvoid || d1Avoid || d2Avoid;
    
    if(machineObvious){
      if(cObivous==true){  //Also we can use the variable here so that we don't have the call the method again.
        writeOnBoard(board, 'o', columnGoX(board, 'o'), columnGoY(board, 'o'));
      }
      else if(rObvious==true){
             writeOnBoard(board, 'o', rowGoX(board, 'o'), rowGoY(board, 'o'));
      }
      else if (d1Obvious==true){
             writeOnBoard(board, 'o', diagonal1Gox(board, 'o'), diagonal1Goy(board, 'o'));
      }
      else if (d2Obvious==true){
             int x = diagonal2Go(board, 'o');
             int y = x;
             writeOnBoard(board, 'o', x, y);
      }
      return true;
    }
    else if (avoidPlayer){                   //The same logic as the "machineObvious".
       if(cAvoid==true){
          writeOnBoard(board, 'o', columnGoX(board, 'x'), columnGoY(board, 'x'));
       }
       else if(rAvoid==true){
          writeOnBoard(board, 'o', rowGoX(board, 'x'), rowGoY(board, 'x'));
       }
       else if (d1Avoid==true){
          writeOnBoard(board, 'o', diagonal1Gox(board, 'x'), diagonal1Goy(board, 'x'));
       }
       else if (d2Avoid==true){
          int x = diagonal2Go(board, 'x');
          writeOnBoard(board, 'o', x, x);
       }
          return true;
    }
    else{
      return false;
    }
  }
  
  public static boolean column (char[][] board, char c){
  
    int counter = 0;
    int x = 0;
    int y = 0;
      
    for (int j = 0; j<board[0].length; j++){
      for (int i = 0; i<board.length; i++){
        if (board[i][j]==c){
            y = j;
            x = i;
            counter++;  //we record the coordinate here, will be used in the following nested loop.
          for (int q = 0; q<board.length; q++){
            if ((board[q][y]==c)&&(q != x)){
              counter++;
            }
          }
          if(counter==(board.length-1)){  
            for (int k = 0; k<board.length; k++){
              if (board[k][y]==' '){  //here we locate the column and loop though the row.
                return true;
              } //if here we have two in a column already and the rest one is a space, that's exactly what we want.
            }
          }
          else{
            counter = 0;
            break;
          }
        }
      }
    }
    return false;
  }
  
  /* The main idea is that we first check if there is a position contain the particular symbol, if there is, then check
   * if there are two symbol. If there are three, or there is only one symbol, then this is not an obvious move and we
   * go to the next cloumn.*/
  
  public static int columnGoX (char[][] board, char c){
  
    //We need the coordinate to tell the AI which position it should move.
    
    int counter = 0;
    int y = 0;
    int x = 0;
      
    for (int j = 0; j<board[0].length; j++){
      for (int i = 0; i<board.length; i++){
        if (board[i][j]==c){
            y = j;
            x = i;
            counter++;
          for (int q = 0; q<board.length; q++){
            if ((board[q][y]==c)&&(q != x)){
              counter++;
            }
          }
          if(counter==(board.length-1)){
            for (int k = 0; k<board.length; k++){
              if (board[k][y]==' '){  
                return k;
              } 
            }
          }
          else{
            counter = 0;
            break;
          }
        }
      }
    }
    return 0;
  }
  //The same method as the column, we return a value this time.
  
    public static int columnGoY (char[][] board, char c){
  
    int counter = 0;
    int y = 0;
    int x = 0;
      
    for (int j = 0; j<board[0].length; j++){
      for (int i = 0; i<board.length; i++){
        if (board[i][j]==c){
            y = j;
            x = i;
            counter++;
          for (int q = 0; q<board.length; q++){
            if ((board[q][y]==c)&&(q != x)){
              counter++;
            }
          }
          if(counter==(board.length-1)){
            for (int k = 0; k<board.length; k++){
              if (board[k][y]==' '){
                return y;
              } 
            }
          }
          else{
            counter = 0;
            break;
          }
        }
      }
    }
    return 0;
  }
  //The same method as the column, we return a value this time.
    
  public static boolean row (char[][] board, char c){
  //The same idea with the column method.
    int counter = 0;
    int x = 0;
    int y = 0;
      
    for (int i = 0; i<board.length; i++){
      for (int j = 0; j<board[0].length; j++){
        if (board[i][j]==c){
            y = j;
            x = i;
            counter++;
          for (int q = 0; q<board.length; q++){
            if ((board[x][q]==c)&&(q != y)){
              counter++;
            }
          }
          if(counter==(board.length-1)){
            for (int k = 0; k<board.length; k++){
              if (board[x][k]==' '){    //here we locate the row and loop though the column.
                return true;
              } 
            }
          }
          else{
            counter = 0;
            break;
          }
        }
      }
    }
    return false;
  }
  
  /* The main idea is that we first check if there is a position contain the particular symbol, if there is, then check
   * if there are two symbol. If there are three, or there is only one symbol, then this is not an obvious move and we
   * go to the next row.*/
  
  public static int rowGoX (char[][] board, char c){
  
    int counter = 0;
    int y = 0;
    int x = 0;
      
    for (int i = 0; i<board.length; i++){
      for (int j = 0; j<board[0].length; j++){
        if (board[i][j]==c){
            y = j;
            x = i;
            counter++;
          for (int q = 0; q<board.length; q++){
            if ((board[x][q]==c)&&(q != y)){
              counter++;
            }
          }
          if(counter==(board.length-1)){
            for (int k = 0; k<board.length; k++){
              if (board[x][k]==' '){
                return x;
              } 
            }
          }
          else{
            counter = 0;
            break;
          }
        }
      }
    }
    return 0;
  }
  //The same method as the row, we return a value this time.
  
    public static int rowGoY (char[][] board, char c){
  
    int counter = 0;
    int y = 0;
    int x = 0;
      
    for (int i = 0; i<board.length; i++){
      for (int j = 0; j<board[0].length; j++){
        if (board[i][j]==c){
            y = j;
            x = i;
            counter++;
          for (int q = 0; q<board.length; q++){
            if ((board[x][q]==c)&&(q != y)){
              counter++;
            }
          }
          if(counter==(board.length-1)){
            for (int k = 0; k<board.length; k++){
              if (board[x][k]==' '){
                return k;
              } 
            }
          }
          else{
            counter = 0;
            break;
          }
        }
      }
    }
    return 0;
  }
    //The same method as the row, we return a value this time.

  public static boolean diagonal1 (char[][] board, char c){
  
    /* Diagonal 1 here means this is one of the two situations we may have when "diagonal", which is the sum of
     * the coordinate x and y is equal to (board.length-1), can be think of the diagonal from the left bottom
     * corner to the right upper corner.*/
    
    int need = board.length-1;
    int have = 0;
    int a = board.length-1;
    
    for (int i = 0; i<board.length; i++){
       if (board[i][a-i]==c){
         have++;
       }
    }
    if (have==need){
      for (int i = 0; i<board.length; i++){
        if (board[i][a-i]==' '){
          return true;
        } 
      }
    }
    return false;
  }
  
  public static boolean diagonal2 (char[][] board, char c){
    /* Diagonal 1 here means this is one of the two situations we may have when "diagonal", which is when
     * the coordinate x and y are the same, can be think of the diagonal from the right bottom
     * corner to the left upper corner.*/
    
    int need = board.length-1;
    int have = 0;
    
    for (int i = 0; i<board.length; i++){
      if (board[i][i]==c){
        have++; //The only situation when a position with same coordinate x and y is in the diagonal 2 situation.
      }
    }
      
    if (have==need){
      for (int i = 0; i<board.length; i++){
        if (board[i][i]==' '){
          return true;
        } 
      }
    }
    return false;
  }
  
  public static int diagonal1Gox (char[][] board, char c){
  
    int a = board.length-1;
    int t = 0;
    
    for (int i = 0; i<board.length; i++){
      if (board[i][a-i]==' '){
         t = i;
      }
    }
    return t;
    //We return the coordinate x only.
  }
  
   public static int diagonal1Goy (char[][] board, char c){
  
    int a = board.length-1;
    int t = 0;
    
    for (int i = 0; i<board.length; i++){
      if (board[i][a-i]==' '){
         t = i;
      }
    }
    
    int y = a-t;

    return y;
    //We return the coordinate y only.
  }
  
  public static int diagonal2Go (char[][] board, char c){

    int t = 0;
      
    for (int i = 0; i<board.length; i++){
      if (board[i][i]==' '){
         t = i;
      }
    }
    
    return t;
    /* In this situation, coordiante x and y are the same, so we can just return t and in the "writeOnBoard" method,
     * use it as both x and y.*/
  }

  public static void getAIMove (char[][] board){
  
    boolean obvious = checkForObviousMove(board);
    //Make sure that the "checkForObviousMove(board)" is called only once!

    if (obvious==false){
      Random r = new Random();
      int random1 = r.nextInt(board.length);
      int random2 = r.nextInt(board.length);
      
      for (int i = 1; i>0; i++){
        if (board[random1][random2] != ' '){
        random1 = r.nextInt(board.length);
        random2 = r.nextInt(board.length);
        }
        if (board[random1][random2] == ' '){
          writeOnBoard(board, 'o', random1, random2);
          break;
        }//The only way to break this infinite loop is to find a empty space to give a random move.
      }
    }
  }
  
  public static char checkForWinner (char[][] board){
  
    boolean c = columnCheck(board, 'o');
    boolean r = rowCheck(board, 'o');
    boolean d1 = diagonal1Check(board, 'o');
    boolean d2 = diagonal2Check(board, 'o');  //Store the boolean so that it won't be repeatedly called.
    boolean machine = c || r || d1 || d2;
    
    boolean pc = columnCheck(board, 'x');
    boolean pr = rowCheck(board, 'x');
    boolean pd1 = diagonal1Check(board, 'x');
    boolean pd2 = diagonal2Check(board, 'x');
    boolean player =  pc || pr || pd1 || pd2;
    
    if(machine){
      return 'o';
    }
    
    if(player){
      return 'x';
    }
      return ' '; //only if neither the machine nor the player win the game, we claim that it is a tie.
  }

  public static boolean columnCheck (char[][] board, char c){
  
    //If there is a column with no space and the other symbol, it is a filled column and we can claim the winner.
    
    int counter = 0;
    char changeSign = 'x';
      
    if(c=='x'){
      changeSign = 'o';
    }
      
    for (int j = 0; j<board[0].length; j++){
      for (int i = 0; i<board.length; i++){
        if ((board[i][j]==' ')||(board[i][j]==changeSign)){
          counter = 0;  
          break;
        }
        else {
          counter++;
          if(counter==board.length){
            return true;
          }
        } 
      }
    }
    return false;
  }

  public static boolean rowCheck (char[][] board, char c){
  
    //If there is a row with no space and the other symbol, it is a filled column and we can claim the winner.
    
    int counter = 0;
    char changeSign = 'x';
      
    if(c=='x'){
      changeSign = 'o';
    }
      
    for (int i = 0; i<board.length; i++){
      for (int j = 0; j<board[0].length; j++){
        if ((board[i][j]==' ')||(board[i][j]==changeSign)){
          counter = 0;  
          break;
        }
        else {
          counter++;
          if(counter==board.length){
             return true;
          }
        } 
      }
    }
    return false;
  }
  
  public static boolean diagonal1Check (char[][] board, char c){
  
    int need = board.length; //this time we need all spaces in that line being filled.
    int have = 0;
    int a = board.length-1;
    
    for (int i = 0; i<board.length; i++){
      if (board[i][a-i]==c){
         have++; //Since we locate the sum of i and j, no inner loop needed.
      }
    }
    if (have==need){
       return true;
    }
    return false;
  }
    
  public static boolean diagonal2Check (char[][] board, char c){
  
    int need = board.length;
    int have = 0;
    
    for (int i = 0; i<board.length; i++){
      if (board[i][i]==c){
        have++; //Since we locate the sum of i and j, no inner loop needed.
      }
    }
    if (have==need){
      return true;
    }
    return false;
  }
}