package com.scaler.TicTacToe.model;

import com.scaler.TicTacToe.model.enums.BotDifficultyLevel;
import com.scaler.TicTacToe.model.enums.PlayerType;
import com.scaler.TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(String name, Symbol symbol, PlayerType playerType) {
        super(name, symbol, playerType);
    }

    public Bot(String botName, Symbol symbol, BotDifficultyLevel botDifficultyLevel, BotPlayingStrategy botPlayingStrategyForDifficultyLevel) {
        super(botName, symbol,PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = botPlayingStrategyForDifficultyLevel;
    }

    public Move makeMove(Board board){
        Move move = botPlayingStrategy.makeMove(board,this);
        move.setPlayer(this);
        return move;
    }
}
