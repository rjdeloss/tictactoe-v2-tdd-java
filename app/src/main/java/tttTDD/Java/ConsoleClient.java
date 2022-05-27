package tttTDD.Java;

import java.util.Scanner;

public class ConsoleClient {
    private GameConfiguration gameConfig;
    private Game game;
    private Scanner input;

    public ConsoleClient() {
        input = new Scanner(System.in);
        setUpConfiguration();
        setUpGame();
    }

    public void setUpConfiguration() {
        askForHumanVsHumanGame();
        String answer = input.next();
        gameConfig = new GameConfiguration(answerToPlayWithComputer(answer));
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
        String message = String.format("Player %s has made a move on space %s", marker, value);
        System.out.println(message);
    }

    private void printGameResult() {
        System.out.println(game.wonBy());
    }

    private void askForHumanVsHumanGame() {
        System.out.println("Is this a Human vs Human Game? [y/n]");
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

    private void renderBoard() {
        String board = game.getBoard();
        String[] splitStringIntoRows = board.split("(?<=\\G.{" + gameConfig.getBoardSizeConfiguration() + "})");
        String[] formattedRowsWithColumn = drawLinesForColumns(splitStringIntoRows);
        board = String.join(drawLineForRows(), formattedRowsWithColumn);

        System.out.println(board);
    }

    private String[] drawLinesForColumns(String[] boardRows) {
        String[] newRows = new String[boardRows.length];

        for(int i = 0; i < boardRows.length; i++) {
            String row = boardRows[i];

            String[] splitRowsIntoColumns = row.split("");
            newRows[i] = String.join("|", splitRowsIntoColumns);
        }

        return newRows;
    }

    private String drawLineForRows() {
        StringBuilder separators = new StringBuilder();
        String[] splitSeparators;
        String joinSeparators;

        for(int i = 0; i < gameConfig.getBoardSizeConfiguration(); i++) {
            separators.append("-");
        }

        splitSeparators = separators.toString().split("");
        joinSeparators = String.join("+", splitSeparators);

        return "\n"+ joinSeparators + "\n";
    }
}
