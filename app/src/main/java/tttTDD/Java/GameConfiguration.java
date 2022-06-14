package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

public class GameConfiguration {
    private int boardSize;
    private boolean isComputer;
    private Player player1;
    private Player player2;

    public GameConfiguration(int boardSize, boolean isComputer) {
        this.boardSize = boardSize;
        this.isComputer = isComputer;
    }

    public GameConfiguration(int boardSize, Player player1, Player player2) {
        this.boardSize = boardSize;
        this.player1 = player1;
        this.player2 = player2;
    }

    public int getBoardSizeConfiguration() {
        return this.boardSize;
    }

    public boolean isComputerPlaying() {
        return this.isComputer;
    }

    public Player getPlayer1() {
        return this.player1;
    }

    public Player getPlayer2() {
        return this.player2;
    }


}
