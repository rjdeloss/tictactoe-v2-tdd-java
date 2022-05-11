package tttTDD.Java;

public class Board {
    private final int DEFAULT_SIZE = 3;
    private final String[] board;


    public Board() {
        this.board = initializeBoard(DEFAULT_SIZE);
    }

    public Board(int boardSize) {
        this.board = initializeBoard(boardSize);
    }

    // refactor this into the abyss!
    public String[] getBoard() {
        return this.board;
    }

    // how do we handle index out of bounds? exception?
    public String getSpace(int location) {
        return this.board[location];
    }

    // bool return
    public boolean updateBoard(int location, String marker) {
        if (isSpaceAvailable(location)) {
            this.board[location] = marker;
            return true;
        }

        return false;
    }

    private String[] initializeBoard(int boardSize) {
        String[] board = new String[boardSize * boardSize];
        for (int i = 0; i < board.length; i++) {
            board[i] = String.valueOf(i + 1);
        }

        return board;
    }

    private boolean isSpaceAvailable(int location) {
        return (String.valueOf(location + 1).equals(getSpace(location)));
    }
}
