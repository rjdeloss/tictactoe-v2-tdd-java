package tttTDD.Java;

public class Board {
    private final String[] board;

    public Board() {
        this.board = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }

    public Board(int boardSize) {
        this.board = new String[boardSize * boardSize];
        for (int i = 0; i < this.board.length; i++) {
            this.board[i] = String.valueOf(i + 1);
        }
    }

    public String[] getBoard() {
        return this.board;
    }

    public String getSpace(int location) {
        return this.board[location];
    }

    public void updateBoard(int location, String marker) throws Exception {
        if (isSpaceAvailable(location)) {
            this.board[location] = marker;
        } else {
            throw new Exception("The space is already occupied");
        }
    }

    private boolean isSpaceAvailable(int location) {
        return (String.valueOf(location + 1).equals(getSpace(location)));
    }
}
