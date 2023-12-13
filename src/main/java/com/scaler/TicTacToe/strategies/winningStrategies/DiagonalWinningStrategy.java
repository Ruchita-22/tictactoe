package com.scaler.TicTacToe.strategies.winningStrategies;

import com.scaler.TicTacToe.exception.GameDrawnException;
import com.scaler.TicTacToe.model.Board;
import com.scaler.TicTacToe.model.Move;
import com.scaler.TicTacToe.model.Player;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{
    private int dimension;
    private int symbolsAdded;
    private HashMap<Character, Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> bottomLeftDiagonalSymbolCount = new HashMap<>();


    public boolean isCellTopLeftDiagonal(int row, int col) {
        return row == col;
    }

    public boolean isCellBottomLeftDiagonal(int row, int col) {
        return (row + col) == dimension - 1;
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();
        int dimension = board.getSize();

        Player lastMovePlayer = lastMove.getPlayer();

        if(checkForDiagonalWin(row, col, symbol, lastMove) != null)
            return lastMovePlayer;

        if(symbolsAdded == (dimension*dimension)) {
            board.printBoard();
            throw new GameDrawnException("Game is drawn as cells are full");
        }

        return null;
    }

    private Player checkForDiagonalWin(int row, int col, char symbol, Move lastMove) {
        /**
         * Logic for diagonal hashmap update and winning
         */
        if (isCellTopLeftDiagonal(row, col)) {
            topLeftDiagonalSymbolCount.put(symbol, topLeftDiagonalSymbolCount.getOrDefault(symbol, 0) + 1);
            if (topLeftDiagonalSymbolCount.get(symbol) == dimension)
                return lastMove.getPlayer();
        }
        if (isCellBottomLeftDiagonal(row, col)) {
            bottomLeftDiagonalSymbolCount.put(symbol, bottomLeftDiagonalSymbolCount.getOrDefault(symbol, 0) + 1);
            if (bottomLeftDiagonalSymbolCount.get(symbol) == dimension)
                return lastMove.getPlayer();
        }
        return null;
    }

}
