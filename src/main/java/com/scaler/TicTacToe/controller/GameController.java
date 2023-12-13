package com.scaler.TicTacToe.controller;

import com.scaler.TicTacToe.model.Game;
import com.scaler.TicTacToe.model.Move;
import com.scaler.TicTacToe.model.Player;
import com.scaler.TicTacToe.model.enums.GameState;
import com.scaler.TicTacToe.strategies.winningStrategies.OrderOneWinningStrategy;
import com.scaler.TicTacToe.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players){
        try {
            return Game.builder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(List.of(new OrderOneWinningStrategy(dimension)))
                    .build();

        }catch (Exception e){
            System.out.println("Count not start the game, something went wrong");
        }
        return null;

    }
    public void displayBoard(Game game){
        game.getBoard().printBoard();
    }
    public GameState getGameState(Game game){
        return game.getGameState();
    }
    public Move excuteMove(Game game, Player player){
        Move move = player.makeMove(game.getBoard());
        updateGameMove(game, move);
        return move;
    }
    private void updateGameMove(Game game, Move move){
        game.getMoves().add(move);

    }
    public Player checkWinner(Game game, Move recentMove){
        for(WinningStrategy winningStrategy : game.getWinningStrategies()){
            Player player = winningStrategy.checkWinner(game.getBoard(), recentMove);
            if(player != null)
                return player;
        }
        return null;
    }

    public String getWinner(Game game){
        return game.getWinner().getName();
    }
}
