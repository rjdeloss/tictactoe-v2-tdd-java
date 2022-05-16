package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

public class Game {
    Board board;
    Player player1;
    Player player2;
    Player currentPlayer;
    boolean gameCompleted;
    String gameWinner;

    public Game(GameConfiguration gameConfig) {
        board = new Board(gameConfig.getBoardSizeConfiguration());
        initializePlayers(gameConfig.isComputerPlaying());
        currentPlayer = player1;
        gameCompleted = false;
    }
    // humanPlayer.move(int) => return int
    // list all the things that it does and all the things that it needs
    public boolean performMove(int playerInput) {
        if (!currentPlayer.isComputer()) {
            board.updateBoard(playerInput, currentPlayer.getMarker());
        } else {
            board.updateBoard(board.getFirstAvailableMove(), currentPlayer.getMarker());
        }
        gameCompleted = gameHasWinner();
        swap();
        return true;
    }

    private boolean gameHasWinner() {
        if (board.hasWinningSet()) {
            gameWinner = String.format("Player %s has won", currentPlayer.getMarker());
        }

        return board.hasWinningSet();
    }

    private void initializePlayers(boolean areWePlayingComputer) {
        player1 = new Human("X");
        player2 = areWePlayingComputer ? new Computer("O") : new Human("O");
    }

    private void swap() {
        currentPlayer = currentPlayer.getMarker().equals(player1.getMarker()) ? player2 : player1;
    }
}
