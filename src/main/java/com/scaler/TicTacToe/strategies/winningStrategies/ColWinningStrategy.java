package com.scaler.TicTacToe.strategies.winningStrategies;

import com.scaler.TicTacToe.exception.GameDrawnException;
import com.scaler.TicTacToe.model.Board;
import com.scaler.TicTacToe.model.Move;
import com.scaler.TicTacToe.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColWinningStrategy implements WinningStrategy{

    private int dimension;
    private int symbolsAdded;
    private List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>(); // []
    private List<HashMap<Character, Integer>> colSymbolCount = new ArrayList<>(); // []


    public ColWinningStrategy(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i < dimension; i++) {
            colSymbolCount.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        int dimension = board.getSize();

        Player lastMovePlayer = lastMove.getPlayer();


         if(checkForColWin(row, col, symbol, lastMove) != null)
            return lastMovePlayer;

        if(symbolsAdded == (dimension*dimension)) {
            board.printBoard();
            throw new GameDrawnException("Game is drawn as cells are full");
        }

        return null;
    }

    private Player checkForColWin(int row, int col, char symbol, Move lastMove){
        /**
         * Logic for col hashmap update and winning
         */
        colSymbolCount.get(col).put(symbol, colSymbolCount.get(col).getOrDefault(symbol, 0) + 1);
        if (colSymbolCount.get(col).get(symbol) == dimension)
            return lastMove.getPlayer();
        return null;
    }

}
