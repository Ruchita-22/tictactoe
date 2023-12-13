package com.scaler.TicTacToe.strategies.botPlayingStrategies;

import com.scaler.TicTacToe.model.Board;
import com.scaler.TicTacToe.model.Move;
import com.scaler.TicTacToe.model.Player;

public interface BotPlayingStrategy {
    public Move makeMove(Board board, Player player);
}
