package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ConsoleClient {
    private PlayerFactory playerFactory = new PlayerFactory();

    private Game game;
    private final Scanner input;
    private GameConfiguration gameConfig;

    public ConsoleClient() throws Exception{
        input = new Scanner(System.in);
        setUpConfiguration();
        setUpGame();
    }

    public void startGame() {
        renderBoard();
        consoleLoop();
    }

    public void consoleLoop() {
        while (!game.isTheGameComplete()) {
            if (!game.getCurrentPlayer().isComputer()) {
                askPlayerToMakeMove();
            }

            performPlayerTurn();
        }

        printGameResult();
    }

    private void setUpConfiguration() {
        int boardSize = askForBoardSize();
        Player player1 = askToCreatePlayer("X");
        Player player2 = askToCreatePlayer("O");

        gameConfig = new GameConfiguration(boardSize, player1, player2);
    }

    private void setUpGame() throws Exception{
        game = new Game(gameConfig);
    }

    private void performPlayerTurn() {
        if (game.performTurn(game.getCurrentPlayerMove())) {
            printTheMovePerformed(game.getCurrentPlayerPreviousMove());
            renderBoard();
        } else {
            askPlayerToPlayAgain();
        }
    }

    private void printTheMovePerformed(int value) {
        String marker = game.getCurrentPlayer().getMarker().equals(game.getPlayer1Marker()) ? game.getPlayer2Marker() : game.getPlayer1Marker();
        String message = String.format("Player %s has made a move on space %s", marker, value);
        System.out.println(message);
    }

    private void printGameResult() {
        System.out.println(game.wonBy());
    }

    private int askForBoardSize() {
        System.out.println("Please enter a board size number:");
        return boardSizeInput();
    }

    private void askPlayerToMakeMove() {
        String message = "Make your move:";
        System.out.println(message);
    }

    private void askPlayerToPlayAgain() {
        String message = "Oops... incorrect move. Please try again";
        System.out.println(message);
    }

    private void renderBoard() {
        int boardSize = gameConfig.getBoardSizeConfiguration();
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

        System.out.println(assembleBoard);
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

    private int boardSizeInput() {
        try {
            int value = input.nextInt();

            if (value > 2) {
                return value;
            } else {
                throw new Exception();
            }
        }

        catch (InputMismatchException e) {
            System.out.println(wrongTypeMessage());
            input.nextLine();
            return  boardSizeInput();
        }

        catch (Exception e) {
            System.out.println(negativeBoardSize());
            input.nextLine();
            return  boardSizeInput();
        }
    }

    private int playerSelectionInput() {
        int value;

        try {
            value = input.nextInt();
            if (value > 0 || value < 4) {
                return value;
            }
        }

        catch (Exception e) {
            System.out.println(wrongTypeMessage());
            input.nextLine();
            return  playerSelectionInput();
        }

        return value;
    }

    private String wrongTypeMessage() {
        return "Stop it. Enter a number as an input:";
    }

    private String negativeBoardSize() {
        return "Can't have the Universe implode on us. Try a larger number:";
    }

    private Player askToCreatePlayer(String marker) {
        int selection = askForPlayerType();

        return  playerFactory.createPlayer(selection, marker, input);
    }

    private int askForPlayerType() {
        System.out.println("What type of player is this?[Enter a number]\n 1. Human\n 2. Computer\n 3. Random Computer\n");
        return playerSelectionInput();
    }
}
