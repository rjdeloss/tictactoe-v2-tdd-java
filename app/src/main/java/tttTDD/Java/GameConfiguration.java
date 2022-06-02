package tttTDD.Java;

public class GameConfiguration {
    private int boardSize;
    private boolean isComputer;

    public GameConfiguration(int boardSize, boolean computerPlayer) {
        this.boardSize = boardSize;
        this.isComputer = computerPlayer;
    }

    public int getBoardSizeConfiguration() {
        return this.boardSize;
    }

    public boolean isComputerPlaying() {
        return this.isComputer;
    }
}
