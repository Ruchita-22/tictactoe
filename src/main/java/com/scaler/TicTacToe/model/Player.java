package com.scaler.TicTacToe.model;

import com.scaler.TicTacToe.model.enums.CellState;
import com.scaler.TicTacToe.model.enums.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Scanner;

@Data
public class Player {
    private static Long idCounter = 0L;
    private Long id;
    private String name;
    private Symbol symbol;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.id = idCounter++;
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }

    public Move makeMove(Board board){
        System.out.println(this.getName()+" Please enter the row and col of the move");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        //validate the move  and throw exception

        board.getBoard().get(row).get(col).setPlayer(this);
        board.getBoard().get(row).get(col).setCellState(CellState.FILLED);
        return new Move(new Cell(row,col,this), this);
    }
}
