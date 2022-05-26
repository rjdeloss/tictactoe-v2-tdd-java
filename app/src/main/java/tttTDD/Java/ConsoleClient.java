package tttTDD.Java;

public class ConsoleRunner {
    GameConfiguration gameConfig;
    Game game;

    public ConsoleRunner() {
        setUpConfiguration();
        setUpGame();
    }

    public void setUpConfiguration() {
        System.out.println("Is this a Human vs Human Game? [y/n]");
        gameConfig = new GameConfiguration(false);
    }

    public Game getGame() {
        return this.game;
    }

    private void setUpGame() {
        game = new Game(gameConfig);
    }
}
