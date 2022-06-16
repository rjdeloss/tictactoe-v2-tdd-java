package tttTDD.Java;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Display {

    public void render(String message) {
        System.out.println(message);
    }

    public String playerSelectionMessage() {
        return "What type of player is this?[Enter a number]\n 1. Human\n 2. Computer\n 3. Random Computer\n";
    }

    public String negativeBoardSizeMessage() {
        return "Can't have the Universe implode on us. Try a larger number:";
    }

    public String wrongTypeMessage() {
        return "Stop it. Enter a number as an input:";
    }

    public String tryAgainMessage() {
        return "Oops... incorrect move. Please try again";
    }

    public String enterBoardSizeMessage() {
        return "Please enter a board size number:";
    }

    public String makeMoveMessage() {
        return "Make your move:";
    }

    public String showMovePerformedMessage(Game game, int value) {
        String marker = game.getCurrentPlayer().getMarker().equals(game.getPlayer1Marker()) ? game.getPlayer2Marker() : game.getPlayer1Marker();
        return String.format("Player %s has made a move on space %s", marker, value);
    }

    public String renderBoard(Game game, int boardSize) {
        String[] board = game.getBoard();
        String largestInputOnBoard = String.valueOf(boardSize * boardSize);
        String separators = drawRowHorizontalLines(boardSize, largestInputOnBoard);

        ArrayList<String> fullBoard = new ArrayList<>();
        ArrayList<String> formattedRow = new ArrayList<>();

        for(int location = 0; location < board.length; location++) {
            String cell = formatCellDisplay(board[location], largestInputOnBoard);
            formattedRow.add(cell);

            int reachedEndOfTheRow = (location + 1) % boardSize;

            if (reachedEndOfTheRow == 0) {
                String linesBetweenCells = String.join(" |", formattedRow);
                fullBoard.add(linesBetweenCells + "\n");
                formattedRow.clear();
            }
        }

        String assembleBoard = String.join(separators +"\n",fullBoard);

        return assembleBoard;
    }

    private String drawRowHorizontalLines(int boardSize, String maxInput) {
        ArrayList<String> separatorCollection = new ArrayList<>();

        String separator = maxInput.replaceAll(".", "-") + "-";
        IntStream.range(0, boardSize).forEach(i -> separatorCollection.add(separator));

        return String.join("+", separatorCollection);
    }

    private String formatCellDisplay(String cell, String maxInput) {
        StringBuilder formattedCell = new StringBuilder();

        if (cell.length() <= maxInput.length()) {

            IntStream.range(0, maxInput.length() - cell.length()).forEach(i -> formattedCell.append(" "));
            formattedCell.append(cell);
        }

        return formattedCell.toString();
    }
}
