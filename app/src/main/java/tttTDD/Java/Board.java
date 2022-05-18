package tttTDD.Java;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
    private final int DEFAULT_SIZE = 3;
    private final String[] board;
    private int numberOfSpaces;
    private int[][] winningSets;

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

    public boolean hasWinningSet(String marker) {
        for (int[] set : this.winningSets) {
            String[] movesBeingChecked = mapBoardMovesToSet(set);

            if (Arrays.stream(movesBeingChecked).allMatch(space -> marker.equals(space))) {
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
//        return Arrays.stream(board).filter((space) -> isSpaceAvailable(board.index)).toArray(String[]::new);
    }

    private String[] mapBoardMovesToSet(int[] set) {
        String[] setBeingChecked = new String[set.length];

        for (int i = 0; i < set.length; i++) {
            setBeingChecked[i] = board[set[i]];
        }

        return setBeingChecked;
    }

    private int[][] createWinningSet(int boardSize) {
        int[][] winningSets = new int[(boardSize * 2) + 2][boardSize];
        int location = 0;
        int k = 0;
        int d = 0;

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                winningSets[i][j] = location;
                location++;
            }
        }

        for(int i = boardSize; i < boardSize*2; i++) {
            for(int j = 0; j < boardSize; j++ ) {
                winningSets[i][j] = winningSets[j][k];
            }
            k++;
        }

        for(int i = boardSize * 2 ; i < boardSize * 2 + 1; i++) {
            for(int j = 0; j < boardSize; j++) {
                winningSets[i][j] = winningSets[j][d++];
            }
            d--;
        }


        for(int i = boardSize * 2 + 1; i < boardSize * 2 + 2; i++) {
            for (int j = 0; j < boardSize; j++) {
                winningSets[i][j] = winningSets[j][d--];

            }
        }

        return winningSets;
    }
}
