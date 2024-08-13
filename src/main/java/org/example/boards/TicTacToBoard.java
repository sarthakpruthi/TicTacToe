package org.example.boards;

import org.example.game.Board;

public class TicTacToBoard extends Board {
    String[][] cells = new String[3][3];

    public TicTacToBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = null;
            }
        }
    }

    public String getCell(int i,int j){
        return cells[i][j];
    }

    public void setCell(int i,int j,String val){
        cells[i][j] = val;
    }

    public void printBoard(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(cells[i][j] != null)
                System.out.print(cells[i][j] + " ");
                else System.out.print("- ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
