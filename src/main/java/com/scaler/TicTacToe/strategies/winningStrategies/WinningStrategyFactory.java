package com.scaler.TicTacToe.strategies.winningStrategies;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategy(int dimension){
        return new OrderOneWinningStrategy(dimension);
    }
}
