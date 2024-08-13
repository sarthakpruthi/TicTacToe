package org.example;

import org.example.boards.TicTacToBoard;
import org.example.game.Board;
import org.example.game.GameResult;
import org.example.game.Move;
import org.example.game.Player;

import java.util.Objects;

public class GameEngine {

    public Board start(String type){
        if(type.equals("TicTacTo")){
            return new TicTacToBoard();
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    public boolean move(Board board, Player player, Move move){
        if(board instanceof TicTacToBoard){
            int x = move.getCell().getRow(), y = move.getCell().getColumn();
            boolean success = false;
            if(x<0 || x>2 || y<0 || y>2) {
                return false;
            }
            if(((TicTacToBoard) board).getCell(x,y)==null){
                ((TicTacToBoard) board).setCell(x,y,player.getSymbol());
                success = true;
            }
            else{
                System.out.println("Chose another position");
            }

            ((TicTacToBoard) board).printBoard();
            return success;
        }
        else{
            throw new IllegalArgumentException();
        }

    }


    public GameResult isComplete(Board board){
        if(board instanceof TicTacToBoard board1){
            //row
            String firstCharacter ="";
            for(int i = 0; i<3; i++){
                firstCharacter = board1.getCell(i,0);
                if(firstCharacter == null){
                    break;
                }
                boolean rowComplete = true;
                for(int j=1;j<3;j++){
                    if(!Objects.equals(firstCharacter, board1.getCell(i, j))){
                        rowComplete = false;
                        break;
                    }
                }
                if(rowComplete){
                    return new GameResult(true,firstCharacter);
                }
            }

            //column
            for(int j=0;j<3;j++){
                firstCharacter = board1.getCell(0,j);
                if(firstCharacter == null){
                    break;
                }
                boolean columnComplete = true;
                for(int i=1;i<3;i++){
                    if(!Objects.equals(firstCharacter, board1.getCell(i, j))){
                        columnComplete = false;
                        break;
                    }
                }
                if(columnComplete){
                    return new GameResult(true,firstCharacter);
                }
            }

            //diagonal
            boolean diagonalComplete = true;
            for(int i=1;i<3;i++){
                firstCharacter = board1.getCell(0,0);
                if(firstCharacter == null || !Objects.equals(firstCharacter, board1.getCell(i, i))){
                    diagonalComplete = false;
                    break;
                }
            }
            if(diagonalComplete){
                return new GameResult(true,firstCharacter);
            }

            //reverse diagonal
            boolean revDiagonalComplete = true;
            for(int i=1;i>=0;i--){
                firstCharacter = board1.getCell(0,2);
                if(firstCharacter == null || !Objects.equals(firstCharacter, board1.getCell(2-i, i))){
                    revDiagonalComplete = false;
                    break;
                }
            }
            if(revDiagonalComplete){
                return new GameResult(true,firstCharacter);
            }


            int cellsFilled = 0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(Objects.nonNull(board1.getCell(i,j))){
                        cellsFilled++;
                    }
                }
            }
            if(cellsFilled == 9){
                return new GameResult(true, null);
            }

        }
        return new GameResult(false,null);
    }

    public void makeEngineMove(TicTacToBoard board, Player p2) {
        int i = (int) ((Math.random()*10)%3), j = (int) ((Math.random()*10)%3);

        if(board.getCell(i,j)!=null){
            makeEngineMove(board,p2);
        }
        else{
            board.setCell(i,j,p2.getSymbol());
            board.printBoard();
        }
    }
}


