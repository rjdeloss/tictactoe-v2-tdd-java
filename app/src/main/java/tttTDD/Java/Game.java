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
        int input = !currentPlayer.isComputer() ? playerInput : board.getFirstAvailableMove();

        if (board.updateBoard(input, currentPlayer.getMarker())) {
            swap();
        } else {
            return false;
        }


        gameCompleted = gameHasWinner();
        return true;
    }

    private boolean gameHasWinner() {
        if (board.hasWinningSet(currentPlayer.getMarker())) {
            gameWinner = String.format("Player %s has won", currentPlayer.getMarker());
        }

        return board.hasWinningSet(currentPlayer.getMarker());
    }

    private void initializePlayers(boolean areWePlayingComputer) {
        player1 = new Human("X");
        player2 = areWePlayingComputer ? new Computer("O") : new Human("O");
    }

    private void swap() {
        currentPlayer = currentPlayer.getMarker().equals(player1.getMarker()) ? player2 : player1;
    }
}
