package tttTDD.Java;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleClient {
    private GameConfiguration gameConfig;
    private Game game;
    private final Scanner input;

    public ConsoleClient() {
        input = new Scanner(System.in);
        setUpConfiguration();
        setUpGame();
    }

    public void setUpConfiguration() {
        askForHumanVsHumanGame();
        String isComputerPlayingInput = input.next();

        askForBoardSize();
        int boardSize = input.nextInt();

        gameConfig = new GameConfiguration(boardSize, answerToPlayWithComputer(isComputerPlayingInput));
    }

    public void startGame() {
        renderBoard();
        consoleLoop();
    }

    public void consoleLoop() {
        while (!game.isTheGameComplete()) {
            int value;
            if (!game.getCurrentPlayer().isComputer()) {
                askPlayerToMakeMove();
                value = convertInputToLocation(input.nextInt());
            } else {
                value = game.getFirstAvailableMove();

            }

            performPlayerTurn(value);
        }

        printGameResult();
    }

    public Game getGame() {
        return this.game;
    }

    private void setUpGame() {
        game = new Game(gameConfig);
    }

    private void performPlayerTurn(int value) {
        if (game.performTurn(value)) {
            printTheMovePerformed(value);
            renderBoard();
        } else {
            askPlayerToPlayAgain();
        }
    }

    private boolean answerToPlayWithComputer(String answer) {
        return !(answer.equals("y") || answer.equals("Y") || answer.equals("yes") || answer.equals("Yes"));
    }

    private void printTheMovePerformed(int value) {
        String marker = game.getCurrentPlayer().getMarker().equals(game.getPlayer1Marker()) ? game.getPlayer2Marker() : game.getPlayer1Marker();
        String message = String.format("Player %s has made a move on space %s", marker, convertLocationToInput(value));
        System.out.println(message);
    }

    private void printGameResult() {
        System.out.println(game.wonBy());
    }

    private void askForHumanVsHumanGame() {
        System.out.println("Is this a Human vs Human Game? [y/n]");
    }

    private void askForBoardSize() {
        System.out.println("Please enter a board size number:");
    }

    private void askPlayerToMakeMove() {
        String message = "Make your move:";
        System.out.println(message);
    }

    private void askPlayerToPlayAgain() {
        String message = "Oops... incorrect move. Please try again";
        System.out.println(message);
    }

    private int convertInputToLocation(int value) {
        return value - 1;
    }

    private int convertLocationToInput(int value) {
        return value + 1;
    }

    private void renderBoard() {
        String[] board = game.getBoard();
        int boardSize = gameConfig.getBoardSizeConfiguration();
        String maxInput = String.valueOf(boardSize * boardSize);

        String rowWithLines;
        ArrayList<String> fullBoard = new ArrayList<>();
        ArrayList<String> formattedRow = new ArrayList<>();
        String separators = drawRowHorizontalLines(boardSize, maxInput);

        for(int i = 0; i < board.length; i++) {
            String space = cellDisplayFormatting(board[i], maxInput);
            formattedRow.add(space);

            if (((i + 1) % boardSize) == 0) {
                rowWithLines = String.join(" |", formattedRow);
                fullBoard.add(rowWithLines + "\n");
                formattedRow.clear();
            }
        }

        System.out.println(String.join(separators +"\n",fullBoard));
    }

    private String drawRowHorizontalLines(int boardSize, String maxInput) {
        ArrayList<String> separatorCollection = new ArrayList<>();
        String separator = "";

        for (int i = 0; i <= maxInput.length(); i++) {
            separator += "-";
        }

        for (int j = 0; j < boardSize; j++) {
            separatorCollection.add(separator);
        }

        return String.join("+", separatorCollection);
    }

    private String cellDisplayFormatting(String cell, String maxInput) {
        String formattedCell = "";
        if (cell.length() <= maxInput.length()) {
            for (int i = 0; i < (maxInput.length() - cell.length()); i++) {
                formattedCell += " ";
            }

            formattedCell += cell;
        }

        return formattedCell;
    }
}
