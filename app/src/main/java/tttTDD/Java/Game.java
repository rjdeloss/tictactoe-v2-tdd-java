package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

public class Game {
    Board board;
    Player player1;
    Player currentPlayer;
//    GameConfiguration gameConfig = new GameConfiguration(3, true);

    public Game(GameConfiguration gameConfig) {
        board = new Board(gameConfig.getBoardSizeConfiguration());
        intializePlayers(gameConfig.isComputerPlaying());
        currentPlayer = player1;
    }

    public boolean performMove(int playerInput) {
        board.updateBoard(playerInput, currentPlayer.getMarker());

        return true;
    }

    private void intializePlayers(boolean areWePlayingComputer) {
        player1 = new Human("X");
    }
}
