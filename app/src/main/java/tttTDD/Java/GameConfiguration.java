package tttTDD.Java;

public class GameConfiguration {
    private int boardSize;
    private boolean isComputer;
    private int DEFAULT_SIZE = 3;

    public GameConfiguration(boolean computerPlayer) {
        this.boardSize = DEFAULT_SIZE;
        this.isComputer = computerPlayer;
    }

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
