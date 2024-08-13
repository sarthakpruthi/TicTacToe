package org.example;

import org.example.boards.TicTacToBoard;
import org.example.game.*;

import java.util.Scanner;


public class Main {
    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine();
        TicTacToBoard board = (TicTacToBoard) gameEngine.start("TicTacTo");
        Scanner s = new Scanner(System.in);
        Player p1 = new Player("Sarthak", "X"), p2 = new Player("Computer", "O");
        while (true){
            GameResult result = gameEngine.isComplete(board);
            if (result.isOver()) {
                System.out.println(p2.getPlayerName() + " is the winner");
                break;
            }

            boolean validInput = false;
            int ro = 0,col = 0;
            while(!validInput){
                try{
                    System.out.println("Enter position of " + p1.getSymbol());
                     ro = s.nextInt();
                     col = s.nextInt();
                     validInput = true;
                }
                catch(Exception e){
                    System.out.println("Please enter a valid input");
                    s.next(); //clear the invalid input
                }
            }

            if(!gameEngine.move(board, p1, new Move(new Cell(ro,col)))) {
                System.out.println("Make a valid move");
                continue;
            }

            result = gameEngine.isComplete(board);
            if(result.isOver()){
                if (result.getWinner() != null) {
                    System.out.println(p1.getPlayerName() + " is the winner");
                } else {
                    System.out.println("Math draw");
                }
                break;
            }

            gameEngine.makeEngineMove(board, p2);
        }

    }
}
