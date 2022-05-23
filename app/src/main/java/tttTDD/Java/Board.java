package tttTDD.Java;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final int DEFAULT_SIZE = 3;
    private final String[] board;
    private int numberOfSpaces;
    private final int[][] winningSets;

    public Board() {
        this.board = initializeBoard(DEFAULT_SIZE);
        this.winningSets = createWinningSet(DEFAULT_SIZE);
    }

    public Board(int boardSize) {
        this.board = initializeBoard(boardSize);
        this.winningSets = createWinningSet(boardSize);
    }

    public String getSpace(int location) {
        return isLocationValid(location) ? null : this.board[location];
    }

    public int getFirstAvailableMove() {
        String[] availableMoves = generateAvailableMoves(board);
        return convertToLocation(availableMoves[0]);
    }

    public boolean updateBoard(int location, String marker) {
        if (isSpaceAvailable(location)) {
            this.board[location] = marker;
            return true;
        }

        return false;
    }

    public boolean hasWinningSet(String marker) {
        for (int[] set : this.winningSets) {
            String[] movesBeingChecked = mapBoardMovesToSet(set);

            if (Arrays.stream(movesBeingChecked).allMatch(marker::equals)) {
              return true;
            }
        }

        return false;
    }

    public boolean hasNoMoreMoves() {
        String[] availableMoves = generateAvailableMoves(board);
        return availableMoves.length == 0;
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

    private String[] generateAvailableMoves(String[] board) {
        ArrayList<String> availableMovesArray = new ArrayList<>();

        for (int i = 0; i < board.length; i++) {
            if (isSpaceAvailable(i)) {
                availableMovesArray.add(board[i]);
            }
        }

        return Arrays.copyOf(availableMovesArray.toArray(), availableMovesArray.size(), String[].class);
    }

    private String[] mapBoardMovesToSet(int[] set) {
        String[] setBeingChecked = new String[set.length];

        for (int i = 0; i < set.length; i++) {
            setBeingChecked[i] = board[set[i]];
        }

        return setBeingChecked;
    }

    private int[][] createWinningSet(int boardSize) {
        int totalPossibleSets = boardSize + boardSize + 2;
        int[][] winningSets = new int[totalPossibleSets][boardSize];

        int location = 0;
        // fills the first boardSize positions in winningSets with the horizontal winners.
        /* winningSets = {
            {0, 1, 2}
            {3, 4, 5}
            {6, 7, 8}
            ...
          }
        */
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                winningSets[i][j] = location;
                location++;
            }
        }

        int vertical = 0;
        // fills the first boardSize positions in winningSets with the vertical winners.
        /* winningSets = {
            ...
            {0, 3, 6}
            {1, 4, 7}
            {2, 6, 8}
            ...
          }
        */
        for(int i = boardSize; i < totalPossibleSets - 2; i++) {
            for(int j = 0; j < boardSize; j++ ) {
                winningSets[i][j] = winningSets[j][vertical];
            }
            vertical++;
        }

        int diagonal = 0;
        // fills the first boardSize positions in winningSets with the diagonal winners.
        /* winningSets = {
            ...
            {0, 4, 8}
            {2, 4, 6}
            ...
          }
        */

        for(int j = 0; j < boardSize; j++) {
            winningSets[totalPossibleSets - 2][j] = winningSets[j][diagonal];
            diagonal++;
        }

        diagonal--;

        for (int j = 0; j < boardSize; j++) {
            winningSets[totalPossibleSets - 1][j] = winningSets[j][diagonal];
            diagonal--;
        }

        return winningSets;
    }
}
