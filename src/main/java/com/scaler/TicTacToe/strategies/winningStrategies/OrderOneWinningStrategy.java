package com.scaler.TicTacToe.strategies.winningStrategies;

import com.scaler.TicTacToe.exception.GameDrawnException;
import com.scaler.TicTacToe.model.Board;
import com.scaler.TicTacToe.model.Move;
import com.scaler.TicTacToe.model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy {
    private int dimension;
    private int symbolsAdded;
    private List<HashMap<Character, Integer>> rowSymbolCount = new ArrayList<>(); // []
    private List<HashMap<Character, Integer>> colSymbolCount = new ArrayList<>(); // []
    private HashMap<Character, Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> bottomLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character, Integer> cornerSymbolCount = new HashMap<>();

    public OrderOneWinningStrategy(int dimension) {
        this.dimension = dimension;
        for (int i = 0; i < dimension; i++) {
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    public boolean isCellTopLeftDiagonal(int row, int col) {
        return row == col;
    }

    public boolean isCellBottomLeftDiagonal(int row, int col) {
        return (row + col) == dimension - 1;
    }

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

        if(checkForRowWin(row, col, symbol, lastMove) != null)
            return lastMovePlayer;
        else if(checkForColWin(row, col, symbol, lastMove) != null)
            return lastMovePlayer;
        else if(checkForDiagonalWin(row, col, symbol, lastMove) != null)
            return lastMovePlayer;
        else if(checkForCornerWin(row, col, symbol, lastMove) != null)
            return lastMovePlayer;

        if(symbolsAdded == (dimension*dimension)) {
            board.printBoard();
            throw new GameDrawnException("Game is drawn as cells are full");
        }

        return null;
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
    private Player checkForColWin(int row, int col, char symbol, Move lastMove){
        /**
         * Logic for col hashmap update and winning
         */
        colSymbolCount.get(col).put(symbol, colSymbolCount.get(col).getOrDefault(symbol, 0) + 1);
        if (colSymbolCount.get(col).get(symbol) == dimension)
            return lastMove.getPlayer();
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