package tttTDD.Java;

import static java.lang.Integer.parseInt;

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

    public void updateBoard(int location, String marker) throws Exception {
        if (isSpaceAvailable(location)) {
            this.board[location] = marker;
        } else {
            throw new Exception();
        }
    }

    private boolean isSpaceAvailable(int location) {
        return (String.valueOf(location + 1) == getSpace(location));
    }
}
