package tttTDD.Java;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final int DEFAULT_SIZE = 3;
    private final String[] board;
    private int numberOfSpaces;
    private int[][] winningSets = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6},
    };

    public Board() {
        this.board = initializeBoard(DEFAULT_SIZE);
    }

    public Board(int boardSize) {
        this.board = initializeBoard(boardSize);
    }

    public String getSpace(int location) {
        return isLocationValid(location) ? null : this.board[location];
    }

    public int getFirstAvailableMove() {
        String[] availableMoves = getAvailableMoves(board);
        return convertToLocation(availableMoves[0]);
    }

    public boolean updateBoard(int location, String marker) {
        if (isSpaceAvailable(location)) {
            this.board[location] = marker;
            return true;
        }

        return false;
    }

    public boolean hasWinningSet() {
        for (int[] set : winningSets) {
            if (board[set[0]].equals(board[set[1]]) && board[set[1]].equals(board[set[2]])) {
                return true;
            }
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

    private int convertToLocation(String space) {
        return Integer.parseInt(space) - 1;
    }

    private boolean isLocationValid(int location) {
        return (location < 0) || (location > numberOfSpaces - 1);
    }

    private String[] getAvailableMoves(String[] board) {
        ArrayList<String> availableMovesArray = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            if (isSpaceAvailable(i)) {
                availableMovesArray.add(board[i]);
            }
        }

        return Arrays.copyOf(availableMovesArray.toArray(), availableMovesArray.size(), String[].class);
    }

}
