package com.tka.tictactoe;
import java.util.Scanner;
public class TicTacToe{
	private static char[][] board = new char[3][3];
    private static char currentPlayer = 'X';
    private static Scanner scanner = new Scanner(System.in);

    public static void initializeBoard() 
    {
        for (int l = 0; l < 3; l++) 
        {
            for (int m = 0; m < 3; m++)
            {
                board[l][m] = ' ';
            }
        }
    }

    public static void printBoard() 
    {
        System.out.println("\n-------------");
        for (int l = 0; l < 3; l++) 
        {
            System.out.print("| ");
            for (int m = 0; m < 3; m++) 
            {
                System.out.print(board[l][m] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    public static boolean isBoardFull() 
    {
        for (int l = 0; l < 3; l++)
        {
            for (int m = 0; m < 3; m++) 
            {
                if (board[l][m] == ' ') 
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkForWin() 
    {
        for (int l = 0; l < 3; l++) 
        {
            if (board[l][0] == board[l][1] && board[l][1] == board[l][2] && board[l][0] != ' ')
            {
                return true;
            }
        }
        
        for (int m = 0; m < 3; m++) {
            if (board[0][m] == board[1][m] && board[1][m] == board[2][m] && board[0][m] != ' ')
            {
                return true;
            }
        }

        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') ||
                (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) 
        {
            return true;
        }
        return false;
    }

    public static void switchPlayer()
    {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) 
    {
        initializeBoard();
        System.out.println("Welcome to Tic-Tac-Toe Game!");

        System.out.println("Select an opponent:");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        int choice = scanner.nextInt();

        if (choice == 1)
        {
            playAgainstHuman();
        } else if (choice == 2)
        {
            playAgainstComputer();
        } else {
            System.out.println("Invalid choice. Exiting...");
        }
        scanner.close();
    }

    public static void playAgainstHuman()
    {
        while (true) {
            printBoard();
            System.out.println("Player " + currentPlayer + ", enter your move (row col): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;

            if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ') 
            {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            board[row][col] = currentPlayer;

            if (checkForWin())
            {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            }

            if (isBoardFull())
            {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            switchPlayer();
        }
    }

    public static void playAgainstComputer()
    {
        while (true)
        {
            if (currentPlayer == 'X') {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row col): ");
                int row = scanner.nextInt() - 1;
                int col = scanner.nextInt() - 1;

                if (row < 0 || row >= 3 || col < 0 || col >= 3 || board[row][col] != ' ')
                {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                board[row][col] = currentPlayer;
            } 
            else {
                                int row, col;
                do {
                    row = (int) (Math.random() * 3);
                    col = (int) (Math.random() * 3);
                } while (board[row][col] != ' ');

                board[row][col] = currentPlayer;
                System.out.println("Computer placed its mark at row " + (row + 1) + ", column " + (col + 1));
            }

            if (checkForWin()) 
            {
                printBoard();
                if (currentPlayer == 'X')
                {
                    System.out.println("Player " + currentPlayer + " wins!");
                } else {
                    System.out.println("Computer wins!");
                }
                break;
            }

            if (isBoardFull()) 
            {
                printBoard();
                System.out.println("It's a draw!");
                break;
            }

            switchPlayer();
        }
    }
}