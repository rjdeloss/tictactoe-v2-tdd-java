package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

import java.util.Scanner;

public class ConsoleClient {
    private PlayerFactory playerFactory = new PlayerFactory();
    private Display display = new Display();

    private GameConfiguration gameConfig;
    private final Scanner input;
    private Game game;

    public ConsoleClient() throws Exception{
        input = new Scanner(System.in);
        setUpConfiguration();
        setUpGame();
    }

    public void startGame() {
        display.render(display.renderBoard(game, gameConfig.getBoardSizeConfiguration()));
        consoleLoop();
    }

    public void consoleLoop() {
        while (!game.isTheGameComplete()) {
            if (!game.getCurrentPlayer().isComputer()) {
                display.render(display.makeMoveMessage());
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
            String movePerformedMessage = display.showMovePerformedMessage(game, game.getCurrentPlayerPreviousMove());
            String gameBoard = display.renderBoard(game, gameConfig.getBoardSizeConfiguration());

            display.render(movePerformedMessage);
            display.render(gameBoard);
        } else {
            display.render(display.tryAgainMessage());
        }
    }

    private void printGameResult() {
        display.render(game.wonBy());
    }

    private int askForBoardSize() {
        display.render(display.enterBoardSizeMessage());
        return GameInput.boardSize(input, display);
    }

    private Player askToCreatePlayer(String marker) {
        display.render(display.playerSelectionMessage());
        int playerType = GameInput.playerSelection(input, display);
        return  playerFactory.createPlayer(playerType, marker, input);
    }
}
