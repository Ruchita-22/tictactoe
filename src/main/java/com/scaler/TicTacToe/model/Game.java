package com.scaler.TicTacToe.model;

import com.scaler.TicTacToe.exception.DuplicateSymbolException;
import com.scaler.TicTacToe.exception.InvalidBotCountException;
import com.scaler.TicTacToe.exception.InvalidDimentionException;
import com.scaler.TicTacToe.exception.InvalidNumberOfPlayerException;
import com.scaler.TicTacToe.model.enums.GameState;
import com.scaler.TicTacToe.model.enums.PlayerType;
import com.scaler.TicTacToe.strategies.winningStrategies.WinningStrategy;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
public class Game {
    private List<Player> players;
    private Board board;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;

    public Game(List<Player> players, Board board, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.board = board;
        this.moves = new ArrayList<Move>();
        this.gameState = GameState.IN_PROGRESS;
        this.nextPlayerIndex = 0;
        this.winningStrategies = winningStrategies;
    }


    public static Builder builder(){
        return new Builder();
    }


    public static class Builder{
        private List<Player> players;
        private List<WinningStrategy> winningStrategies;
        private int dimension;

        private Builder() {
            this.players = new ArrayList<>();
            this.winningStrategies = new ArrayList<>();
            this.dimension = 0;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder setWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
            return this;
        }

        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public void addPlayer(Player player){
            players.add(player);
        }

        public void addWinningStrategy(WinningStrategy winningStrategy){
            winningStrategies.add(winningStrategy);
        }
        // validation
        private void validateBotCounts(){
            int botCount = 0;
            for (Player player : players){
                if (player.getPlayerType().equals(PlayerType.BOT))
                    botCount++;
            }
            if (botCount > 1){
                // throw exception
                throw new InvalidBotCountException("Bot count has exceeded 1");
            }
        }
        private void validateDimension(){
            if(dimension < 3 || dimension > 10)
                throw new InvalidDimentionException("Dimention is invalid, it should between 3 to 10");
        }
        private void validateNumberOfPlayers(){
            if(players.size() != dimension-1)
                throw new InvalidNumberOfPlayerException("Number of player should be equal to "+ (dimension-1));
        }
        private void validateUniqueSymbolForAllPlayers(){
            HashSet<Character> set = new HashSet<>();
            for (Player player : players){
                set.add(player.getSymbol().getSymbolChar());
            }
            if(set.size() != players.size())
                throw new DuplicateSymbolException("Every Player have Unique Symbol");
        }
        private void validate(){
            validateBotCounts();
            validateDimension();
            validateNumberOfPlayers();
            validateUniqueSymbolForAllPlayers();
        }
        public Game build(){
            validate();
            return new Game(players, new Board(dimension), winningStrategies);
        }
    }

}
