package com.scaler.TicTacToe;

import com.scaler.TicTacToe.controller.GameController;
import com.scaler.TicTacToe.model.*;
import com.scaler.TicTacToe.model.enums.BotDifficultyLevel;
import com.scaler.TicTacToe.model.enums.GameState;
import com.scaler.TicTacToe.model.enums.PlayerType;
import com.scaler.TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategy;
import com.scaler.TicTacToe.strategies.botPlayingStrategies.BotPlayingStrategyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class TicTacToeApplication {

	public static void main(String[] args) {

		SpringApplication.run(TicTacToeApplication.class, args);
		System.out.println("Welcome to Tic Tac Toe");

		Scanner sc = new Scanner(System.in);
		GameController gameController = new GameController();

		//Enter Game Details
		System.out.println("Please enter the dimention of the game");
		int dimension = sc.nextInt();

		System.out.println("Will there be any Bot");
		String isBotPrensent = sc.next();

		List<Player> players = new ArrayList<>();
		int iteratorNumber = isBotPrensent.endsWith("Y") ? dimension-2 : dimension-1;

		// Enter Player Details

		for (int i = 0; i < iteratorNumber; i++) {

			System.out.println("Enter the name of Player number "+ i+1);
			String playerName = sc.next();

			System.out.println("Enter the character symbol of Player number "+ i+1);
			String characterSymbol = sc.next();

			Player player = new Player(playerName,new Symbol(characterSymbol.charAt(0)), PlayerType.HUMAN);
			players.add(player);

		}

		// Enter Bot Details
		if(isBotPrensent.endsWith("Y")){

			System.out.println("Enter the name of Bot ");
			String botName = sc.next();

			System.out.println("Enter the character symbol of Bot ");
			String botCharacterSymbol = sc.next();
			System.out.println("Enter the Bot Difficulty Level EASY, MEDIUM, HARD ");
			String botDifficultyLevel1 = sc.next();
			BotDifficultyLevel botDifficultyLevel = BotDifficultyLevel.EASY;
			switch (botDifficultyLevel1){
				case "EASY" : botDifficultyLevel = BotDifficultyLevel.EASY;
				case "MEDIUM" : botDifficultyLevel = BotDifficultyLevel.MEDIUM;
				case "HARD" : botDifficultyLevel = BotDifficultyLevel.HARD;
			}

			Bot bot = new Bot(botName,new Symbol(botCharacterSymbol.charAt(0)), botDifficultyLevel, BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(botDifficultyLevel));
			players.add(bot);
		}
		Collections.shuffle(players);
        Game game = gameController.createGame(dimension,players);

		// Game playing start
		int playerIndex = 0;
		while (gameController.getGameState(game).equals(GameState.IN_PROGRESS)){

			System.out.println("Current Board State");
			gameController.displayBoard(game);

			// get fair chance to each player
			playerIndex++;
			playerIndex = playerIndex % players.size();

			//unto is remaining
			Move movePlayed = gameController.excuteMove(game, players.get(playerIndex));
			Player winner = gameController.checkWinner(game,movePlayed);
			if(winner != null){
				gameController.displayBoard(game);
				System.out.println("Winner is : " + winner.getName());
				break;
			}
		}
	}

}
