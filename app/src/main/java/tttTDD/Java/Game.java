package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

public class Game {
    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
//    GameConfiguration gameConfig = new GameConfiguration(3, true);

    public Game(GameConfiguration gameConfig) {
        board = new Board(gameConfig.getBoardSizeConfiguration());
        initializePlayers(gameConfig.isComputerPlaying());
        currentPlayer = player1;
    }

    public boolean performMove(int playerInput) {
        if (!currentPlayer.isComputer()) {
            board.updateBoard(playerInput, currentPlayer.getMarker());
        } else {
            board.updateBoard(board.getFirstAvailableMoves(), currentPlayer.getMarker());
        }
        swap();
        return true;
    }

    private void initializePlayers(boolean areWePlayingComputer) {
        player1 = new Human("X");
        player2 = areWePlayingComputer ? new Computer("O") : new Human("O");
    }

    private void swap() {
        currentPlayer = currentPlayer.getMarker().equals(player1.getMarker()) ? player2 : player1;
    }
}
