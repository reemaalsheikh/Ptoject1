import java.util.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static ArrayList<Integer> playerPositions = new ArrayList<>();
    static ArrayList<Integer> computerPositions = new ArrayList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // int playerPosition=0;
        //int computerPosition=0;

        System.out.println("Welcome to Tic Tac Game!");
        System.out.println("You have two options:");
        System.out.println("1. Play 1 Round");
        System.out.println("2. Play 3 Rounds");

        int choice = input.nextInt();
        if (choice == 1) {
            System.out.println("You are Playing 1 round");
            playRound(input);
        } else if (choice == 2) {
            System.out.println("You are Playing 3 rounds");
            playthreeRounds(input);
        } else {
            System.out.println("Invalid choice! Try again.");
        }



    }//End of the main


    public static void printGameBoard(char[][] gameBoard) {
        //For each row inside the game board
        //for each symbol inside the row
        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();

        }
    }

    public static void placesymbolXO(char[][] gameBoard, int position, String user) {

        char symbol = ' ';
        if (user.equalsIgnoreCase("Player")) {
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equalsIgnoreCase("Computer")) {
            symbol = 'O';
            computerPositions.add(position);
        }

        switch (position) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }


    public static String checkWinner(char[][] gameBoard) {

        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);

        List leftCol = Arrays.asList(1, 4, 7);
        List middleCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);

        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(7, 5, 3);

        List<List> winningCond = new ArrayList<List>();
        winningCond.add(topRow);
        winningCond.add(middleRow);
        winningCond.add(bottomRow);
        winningCond.add(leftCol);
        winningCond.add(middleCol);
        winningCond.add(rightCol);
        winningCond.add(cross1);
        winningCond.add(cross2);

        String result = "";

        for (List w : winningCond) {

            if (playerPositions.containsAll(w)) {
                printGameBoard(gameBoard);
                result = "You won, Congratulations!";
                break;

            } else if (computerPositions.containsAll(w)) {
                printGameBoard(gameBoard);
                result = "You lost,Hard Luck!";
                break;

                //if the board is full (No one wins)
            } else if (playerPositions.size() + computerPositions.size() == 9) {
                printGameBoard(gameBoard);
                result = "it's a draw :)";
                break;
            }
        }
        return result;
    }

    public static void playRound(Scanner input) {

     char [] [] gameBoard = {{' ', '|',' ','|',' '}, {'-', '+','-','+','-'},
                                  {' ', '|',' ','|',' '}, {'-', '+','-','+','-'}, {' ', '|',' ','|',' '}};

        while(true) {

        System.out.println("Please enter your position (1-9): ");
        int playerposition = input.nextInt();
        while (playerPositions.contains(playerposition) || computerPositions.contains(playerposition) ) {
         System.out.println("Position is taken! Enter another position ");
         playerposition = input.nextInt();
        }
        placesymbolXO(gameBoard, playerposition, "Player");
         String result = checkWinner(gameBoard);

        //check the winner and the result after each move by the player
         if(result.length() > 0) {
            System.out.println(result);
            break;
        }


        //computer choose random position
        Random random = new Random();
        int computerPosition = random.nextInt(9) + 1;
        while(computerPositions.contains(computerPosition) || playerPositions.contains(computerPosition)) {
            computerPosition = random.nextInt(9) + 1;
        }

        placesymbolXO(gameBoard, computerPosition, "Computer");

        printGameBoard(gameBoard);
        result = checkWinner(gameBoard);
       // System.out.println(result);
       // check the winner and the result after each move by the computer
        if(result.length() > 0) {
            System.out.println(result);
            break;
        }

    }

        }



    public static void playthreeRounds(Scanner input) {


        for (int i = 1; i <= 3; i++) {
            System.out.println("Round " + i + ":");
            playRound(input);

            playerPositions.clear();
            computerPositions.clear();
        }
    }






}
















