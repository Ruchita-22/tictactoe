package com.scaler.TicTacToe.strategies.winningStrategies;

import com.scaler.TicTacToe.model.Board;
import com.scaler.TicTacToe.model.Move;
import com.scaler.TicTacToe.model.Player;

public interface WinningStrategy {
    Player checkWinner(Board board, Move move);
}
