package tttTDD.Java;

public class Board {
    private final int DEFAULT_SIZE = 3;
    private final String[] board;
    private int numberOfSpaces;


    public Board() {
        this.board = initializeBoard(DEFAULT_SIZE);
    }

    public Board(int boardSize) {
        this.board = initializeBoard(boardSize);
    }

    public String getSpace(int location) {
        return isLocationValid(location) ? null : this.board[location];
    }

    public boolean updateBoard(int location, String marker) {
        if (isSpaceAvailable(location)) {
            this.board[location] = marker;
            return true;
        }

        return false;
    }

    private String[] initializeBoard(int boardSize) {
        numberOfSpaces = boardSize * boardSize;
        String[] board = new String[numberOfSpaces];
        for (int i = 0; i < board.length; i++) {
            board[i] = String.valueOf(i + 1);
        }

        return board;
    }

    private boolean isSpaceAvailable(int location) {
        return (String.valueOf(location + 1).equals(getSpace(location)));
    }

    private boolean isLocationValid(int location) {
        return (location < 0) || (location > numberOfSpaces - 1);
    }
}
