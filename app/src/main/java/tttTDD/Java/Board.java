package tttTDD.Java;

public class Board {
    private String[] board;

    public Board() {
        this.board = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }

    public String[] getBoard() {
        return this.board;
    }

    public String getSpace(int location) {
        return this.board[location];
    }

    public void updateBoard(int location, String marker) {
        this.board[location] = marker;
    }
}
