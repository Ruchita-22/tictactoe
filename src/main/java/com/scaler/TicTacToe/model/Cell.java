package com.scaler.TicTacToe.model;

import com.scaler.TicTacToe.model.enums.CellState;
import lombok.Data;

@Data
public class Cell {
    private int row;
    private int col;
    private CellState cellState;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.EMPTY;
    }

    public Cell(int row, int col, Player player) {
        this.row = row;
        this.col = col;
        this.cellState = CellState.FILLED;
        this.player = player;
    }

    public void displayCell(){
        if (player == null)
            System.out.print("| |");
        else if (cellState.equals(CellState.BLOCKED))
            System.out.print("|-|");
        else
            System.out.print("|"+player.getSymbol().getSymbolChar()+"|");
    }
}
