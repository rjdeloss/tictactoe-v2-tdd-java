package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tttTDD.Java.Interfaces.Player;
import tttTDD.Java.doubles.TestPlayer;


public class GameTest {
    Game game;
    GameConfiguration gameConfig;
    Player player1;
    Player player2;
    Player testPlayer;

    @Before
    public void setup() {
        player1 = new Human("X");
        player2 = new Computer("O");
        gameConfig = new GameConfiguration(3, player1, player2);
    }

    @Test
    public void PlayerShouldUpdateBoardWithinTheGame3x3() {
        // Arrange
        int[] playerInput = {3};
        testPlayer = new TestPlayer("X", playerInput);

        game = createGameWithoutException(gameConfig);

        // Act
        boolean result = game.performTurn(testPlayer.move(new Board()));

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void PerformTurnShouldReturnFalseWhenASpaceIsTaken() {
        // Arrange
        Player player1 = new TestPlayer("X", new int[]{0, 0});
        Player player2 = new TestPlayer("O", new int[]{1});
        GameConfiguration configuration = new GameConfiguration(3, player1, player2);
        Game game = createGameWithoutException(configuration);

        // Act
        game.performTurn(game.getCurrentPlayerMove());
        game.performTurn(game.getCurrentPlayerMove());
        boolean result = game.performTurn(game.getCurrentPlayerMove());

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void GameShouldSwapPlayersAfterSuccessfulMoveOnTheBoard() {
        Player player1 = new TestPlayer("X", new int[]{3});
        Player player2 = new TestPlayer("O", new int[]{});
        GameConfiguration configuration = new GameConfiguration(3, player1, player2);
        Game game = createGameWithoutException(configuration);
        // Act
        game.performTurn(game.getCurrentPlayerMove());

        // Assert
        Player nextPlayer = game.getCurrentPlayer();
        Assert.assertNotEquals(player1.getMarker(), nextPlayer.getMarker());
    }

    @Test
    public void GameShouldGenerateHumanPlayerAsFirstPlayerIfConfigFlagIsTrue(){
        game = createGameWithoutException(gameConfig);

        Assert.assertEquals(game.getPlayer1Marker(), "X");
        Assert.assertFalse(game.checkPlayer1AIStatus());
    }

    @Test
    public void GameShouldGenerateComputerPlayerAsSecondPlayerIfConfigFlagIsTrue(){
        game = createGameWithoutException(gameConfig);

        Assert.assertTrue(game.checkPlayer2AIStatus());
    }

    @Test
    public void ChangeGameStateGameCompletedToTrueIfWinningSetFound() {
        Player player1 = new TestPlayer("X", new int[]{0, 3, 6});
        Player player2 = new TestPlayer("O", new int[]{1, 2});
        GameConfiguration configuration = new GameConfiguration(3, player1, player2);
        Game game = createGameWithoutException(configuration);

        playTurns(game);

        Assert.assertTrue(game.isTheGameComplete());
    }

    @Test
    public void ChangeGameStateGameCompletedToFalseIfWinningSetNotFound() {
        Player player1 = new TestPlayer("X", new int[]{8, 3});
        Player player2 = new TestPlayer("O", new int[]{1, 2});
        GameConfiguration configuration = new GameConfiguration(3, player1, player2);
        Game game = createGameWithoutException(configuration);

        playTurns(game);

        Assert.assertFalse(game.isTheGameComplete());
    }

    @Test
    public void GameShouldListIfTheGameHasConcludedAndWinner() {
        Player player1 = new TestPlayer("X", new int[]{0, 3, 6});
        Player player2 = new TestPlayer("O", new int[]{1, 2});
        GameConfiguration configuration = new GameConfiguration(3, player1, player2);
        Game game = createGameWithoutException(configuration);

        playTurns(game);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "Player X has won");
    }

    @Test
    public void GameShouldListIfTheGameHasConcludedAndAComputerWon() {
        Player player1 = new TestPlayer("X", new int[]{0, 3, 5, 8});
        Player player2 = new TestPlayer("O", new int[]{1, 2, 4, 7});
        GameConfiguration configuration = new GameConfiguration(3, player1, player2);
        Game game = createGameWithoutException(configuration);

        playTurns(game);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "Player O has won");
    }

    @Test
    public void GameShouldNotListWinnerIfTheGameConcludedAsATie() {
        Player player1 = new TestPlayer("X", new int[]{4, 1, 6, 5, 8});
        Player player2 = new TestPlayer("O", new int[]{0, 2, 3, 7});
        GameConfiguration configuration = new GameConfiguration(3, player1, player2);
        Game game = createGameWithoutException(configuration);

        playTurns(game);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "It's a tie");
    }

    @Test
    public void testPlayerSetup() {
        Player player1 = new TestPlayer("X", new int[]{1, 2, 4, 7});
        Player player2 = new TestPlayer("O", new int[]{8, 3, 6});
        GameConfiguration configuration = new GameConfiguration(3, player1, player2);
        Game game = createGameWithoutException(configuration);

        Assert.assertEquals(1, game.getCurrentPlayerMove());
        game.performTurn(1);
        Assert.assertEquals(8, game.getCurrentPlayerMove());
        game.performTurn(8);
        Assert.assertEquals(2, game.getCurrentPlayerMove());
    }

    private void playTurns(Game game) {
        try  {
            while (!game.isTheGameComplete()) {
                int move = game.getCurrentPlayerMove();
                game.performTurn(move);
            }
        }
        catch  (ArrayIndexOutOfBoundsException e) {

        }
    }

    private Game createGameWithoutException(GameConfiguration gameConfig) {
        try {
            return new Game(gameConfig);
        }

        catch (Exception e) {
            return null;
        }
    }
}
