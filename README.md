# Low Level Design - Tic Tac Toe

### What is TicTacToe?

TicTacToe is a 2 player game played on a 3 x 3 board. Each player is allotted a symbol (one X and one O). Initially, the board is empty. Alternatively, each player takes a turn and puts their symbol at any empty slot. The first player to get their symbol over a complete row OR a complete column OR a diagonal wins.
You can play the game within Google Search by just searching for “tictactoe”!

### Questions to Ask

- Will the game be played amongst only 2 players or can there be any number of players in future?
- Is the board size restricted to 3x3 or can it be any NxN?
- Feature Suggestions:
    - Do we want to time a move? Skip/ Declare the other person as winner if the move doesn’t happen within x seconds.
    - Do we want to support undo operation?
    - Can there be some players who are just watching? Not playing. 
    - Do we want to store analytics? Basically previous games, who played what move etc. 
    - Can one of the players be a bot? 
    - Support for tournaments? Basically a set of matches, each match between 2 players of the tournament. 
    - Can there be different ways to win?

### Expectations

- The code should be working and functionally correct
- Good software design practices should be followed:
- Code should be modular, readable, extensible
- Separation of concern should be addressed
- Project structured well across multiple files/ packages
- Write unit testsNo need to create
- No need of GUI

### Problem Requirements

- Board can be of any NxN size.
- There can be any number of players. Each player will be allotted a symbol.
- The players can be either humans or bots. Each human player will have a name.
- We should allow support to undo any number of moves.
- Try to implement the winner detection algorithm in O(1). 

### Interaction Format

Command Line based interactive application.

#### Input/ Outputs

Game Start

Allows to start the game with a given number of players, board size, and symbols of every player.

StartGame [Number of Players] [... User ID and Symbol for each player separated by space ...] [Board Size]


Example:

StartGame 2 u1 X u2 O 3


If a player is a computer, their user id will be C.

### Class Diagram

![Class Diagram](src/main/resources/images/TicTacToe_Class_Diagram.png)

### Output

    Welcome to Tic Tac Toe
    Please enter the dimention of the game
    3
    Will there be any Bot
    Y
    Enter the name of Player number 01
    Ruchita
    Enter the character symbol of Player number 01
    R
    Enter the name of Bot
    Bot
    Enter the character symbol of Bot
    B
    Enter the Bot Difficulty Level EASY, MEDIUM, HARD
    EASY
    Current Board State
    | || || |
    | || || |
    | || || |
    Ruchita Please enter the row and col of the move
    0 0
    Current Board State
    |R|| || |
    | || || |
    | || || |
    Current Board State
    |R||B|| |
    | || || |
    | || || |
    Ruchita Please enter the row and col of the move
    1 1
    Current Board State
    |R||B|| |
    | ||R|| |
    | || || |
    Current Board State
    |R||B||B|
    | ||R|| |
    | || || |
    Ruchita Please enter the row and col of the move
    2 2
    |R||B||B|
    | ||R|| |
    | || ||R|
    Winner is : Ruchita
    
Game - Board -(i,j) - Cell - Player -  Symbol - 

Move(Cell, Player)
