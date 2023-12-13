package com.scaler.TicTacToe.strategies.winningStrategies;

import com.scaler.TicTacToe.exception.GameDrawnException;
import com.scaler.TicTacToe.model.Board;
import com.scaler.TicTacToe.model.Move;
import com.scaler.TicTacToe.model.Player;

import java.util.HashMap;

public class CornerWinningStrategy implements WinningStrategy{
    private int dimension;
    private int symbolsAdded;
    private HashMap<Character, Integer> cornerSymbolCount = new HashMap<>();

    public boolean isCornerCell(int row, int col) {
        if (row == 0 || row == dimension - 1) {
            if (col == 0 || col == dimension - 1)
                return true;
        }
        return false;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        int dimension = board.getSize();

        Player lastMovePlayer = lastMove.getPlayer();

         if(checkForCornerWin(row, col, symbol, lastMove) != null)
            return lastMovePlayer;

        if(symbolsAdded == (dimension*dimension)) {
            board.printBoard();
            throw new GameDrawnException("Game is drawn as cells are full");
        }

        return null;
    }

    private Player checkForCornerWin(int row, int col, char symbol, Move lastMove) {
        /**
         * Check for corner
         */
        if (isCornerCell(row, col)) {
            cornerSymbolCount.put(symbol, cornerSymbolCount.getOrDefault(symbol, 0) + 1);
            if (cornerSymbolCount.get(symbol) == dimension)
                return lastMove.getPlayer();
        }
        return null;
    }
}
