package com.scaler.TicTacToe.strategies.winningStrategies;

import com.scaler.TicTacToe.model.Board;
import com.scaler.TicTacToe.model.Move;
import com.scaler.TicTacToe.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RowWinningStrategy implements WinningStrategy{
    private int dimension;
    private int symbolsAdded;
    private List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>(); // []
    public RowWinningStrategy(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i < dimension; i++) {
            rowSymbolCount.add(new HashMap<>());

        }
    }
    private Player checkForRowWin(int row, int col, char symbol, Move lastMove){
        /**
         * Logic for row hashmap update and winning
         */
        rowSymbolCount.get(row).put(symbol, rowSymbolCount.get(row).getOrDefault(symbol, 0) + 1);
        if (rowSymbolCount.get(row).get(symbol) == dimension)
            return lastMove.getPlayer();

        return null;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        int dimension = board.getSize();

        Player lastMovePlayer = lastMove.getPlayer();

        if(checkForRowWin(row, col, symbol, lastMove) != null)
            return lastMovePlayer;
        return null;
    }
}
