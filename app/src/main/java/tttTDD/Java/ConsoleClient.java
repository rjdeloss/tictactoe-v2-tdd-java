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
        System.out.println("Is this a Human vs Human Game? [y/n]");
        String answer = input.next();
        gameConfig = new GameConfiguration(playWithComputer(answer));
    }

    public void startGame() {
        renderBoard();
        consoleLoop();
    }

    public void consoleLoop() {
        while (!game.isTheGameComplete()) {
            int value;
            if (!game.getCurrentPlayer().isComputer()) {
                System.out.println("Make your move:");
                value = convertInputToLocation(input.nextInt());
            } else {
                value = game.getFirstAvailableMove();

            }

            game.performTurn(value);
            renderBoard();
        }
    }

    public void renderBoard() {
        String board = game.getBoard();
        String[] splitStringIntoRows = board.split("(?<=\\G.{" + gameConfig.getBoardSizeConfiguration() + "})");
        board = String.join("\n---\n", splitStringIntoRows);

        System.out.println(board);
    }

    public Game getGame() {
        return this.game;
    }

    private void setUpGame() {
        game = new Game(gameConfig);
    }

    private boolean playWithComputer(String answer) {
        return !(answer.equals("y") || answer.equals("Y") || answer.equals("yes") || answer.equals("Yes"));
    }

    private int convertInputToLocation(int value) {
        return value - 1;
    }
}
