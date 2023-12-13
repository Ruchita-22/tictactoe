package com.scaler.TicTacToe.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    private int size;
    private List<List<Cell>> board;

    public Board(int size) {
        this.size = size;

        this.board = new ArrayList<>();

        for (int i=0; i<size; i++){
            this.getBoard().add(new ArrayList<>());
            for (int j=0; j<size; j++){     // add cell
                this.getBoard().get(i).add(new Cell(i,j));
            }
        }
    }
    public void printBoard(){
        for (int i = 0; i < size; i++) {
            List<Cell> row = board.get(i);
            for (int j = 0; j < size; j++) {
                row.get(j).display();
            }
            System.out.println();
        }
    }
}
