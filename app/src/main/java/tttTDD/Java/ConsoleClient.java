package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleClient {
    private PlayerFactory playerFactory = new PlayerFactory();
    private Display display = new Display();

    private Game game;
    private final Scanner input;
    private GameConfiguration gameConfig;

    public ConsoleClient() throws Exception{
        input = new Scanner(System.in);
        setUpConfiguration();
        setUpGame();
    }

    public void startGame() {
        display.renderBoard(game, gameConfig.getBoardSizeConfiguration());
        consoleLoop();
    }

    public void consoleLoop() {
        while (!game.isTheGameComplete()) {
            if (!game.getCurrentPlayer().isComputer()) {
                System.out.println(display.makeMoveMessage());
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
            System.out.println(display.showMovePerformedMessage(game, game.getCurrentPlayerPreviousMove()));;
            System.out.println(display.renderBoard(game, gameConfig.getBoardSizeConfiguration()));;
        } else {
            System.out.println(display.tryAgainMessage());
        }
    }

    private void printGameResult() {
        System.out.println(game.wonBy());
    }

    private int askForBoardSize() {
        System.out.println(display.enterBoardSizeMessage());
        return boardSizeInput();
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
            System.out.println(display.wrongTypeMessage());
            input.nextLine();
            return  boardSizeInput();
        }

        catch (Exception e) {
            System.out.println(display.negativeBoardSizeMessage());
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
            System.out.println(display.wrongTypeMessage());
            input.nextLine();
            return  playerSelectionInput();
        }

        return value;
    }

    private Player askToCreatePlayer(String marker) {
        int selection = askForPlayerType();

        return  playerFactory.createPlayer(selection, marker, input);
    }

    private int askForPlayerType() {
        System.out.println(display.playerSelectionMessage());

        return playerSelectionInput();
    }
}
