package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

public class Game {
    private Player currentPlayer;
    private boolean gameCompleted;
    private String gameWinner;
    private Board board;
    private Player player1;
    private Player player2;


    public Game(GameConfiguration gameConfig) {
        board = new Board(gameConfig.getBoardSizeConfiguration());
        initializePlayers(gameConfig.isComputerPlaying());
        currentPlayer = player1;
        gameCompleted = false;
    }

    public boolean isTheGameComplete() {
        return gameCompleted;
    }

    public String wonBy() {
        return  gameWinner;
    }

    public String getPlayer1Marker() {
        return player1.getMarker();
    }

    public String getPlayer2Marker() {
        return player2.getMarker();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean checkPlayer1AIStatus() {
        return player1.isComputer();
    }

    public boolean checkPlayer2AIStatus() {
        return player2.isComputer();
    }

    public String getBoardSpace(int location) {
        return  board.getSpace(location);
    }

    public int getBoardFirstAvailableMove() {
        return board.getFirstAvailableMove();
    }

    public boolean performMove(int playerInput) {
        int input = !currentPlayer.isComputer() ? playerInput : board.getFirstAvailableMove();

        if (board.updateBoard(input, currentPlayer.getMarker())) {
            gameCompleted = gameHasFinished();
            swap();
        } else {
            return false;
        }

        return true;
    }

    private boolean gameHasFinished() {
        if (isThereAWinner() && !isThereATie()) {
            gameWinner = String.format("Player %s has won", currentPlayer.getMarker());
        } else {
            gameWinner ="";
        }

        return isThereAWinner() || isThereATie();
    }

    private boolean isThereATie() {
        return board.hasNoMoreMoves() && (!isThereAWinner());
    }

    private boolean isThereAWinner() {
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
