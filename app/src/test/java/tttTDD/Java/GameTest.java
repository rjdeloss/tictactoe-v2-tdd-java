package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tttTDD.Java.Interfaces.Player;
import tttTDD.Java.doubles.TestPlayer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;


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
        boolean result = game.performTurn(testPlayer);

        // Assert
        Assert.assertTrue(result);
    }

    @Test
    public void PerformTurnShouldReturnFalseWhenASpaceIsTaken() {
        // Arrange
        game = createGameWithoutException(gameConfig);
        int[] moves = {0, 1};
        // Moves played by computer => 1
        performGameTurns(moves);

        // Act
        int [] invalidMove = {0};
        testPlayer = new TestPlayer("X", invalidMove);
        boolean result = game.performTurn(testPlayer);

        // Assert
        Assert.assertFalse(result);
    }

    @Test
    public void GameShouldSwapPlayersAfterSuccessfulMoveOnTheBoard() {
        int[] playerInput = {3};
        game = createGameWithoutException(gameConfig);
        testPlayer = new TestPlayer("X", playerInput);
        // Act
        game.performTurn(testPlayer);

        // Assert
        Player nextPlayer = game.getCurrentPlayer();
        Assert.assertNotEquals(testPlayer.getMarker(), nextPlayer.getMarker());
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
        game = createGameWithoutException(gameConfig);
        int[] movesPlayed = {0, 1, 3, 2, 6};
        // Moves played by computer => 1, 2

        performGameTurns(movesPlayed);

        Assert.assertTrue(game.isTheGameComplete());
    }

    @Test
    public void ChangeGameStateGameCompletedToFalseIfWinningSetNotFound() {
        game = createGameWithoutException(gameConfig);
        int[] movesPlayed = {8, 1, 3, 2};
        // Moves played by computer => 1, 2

        performGameTurns(movesPlayed);

        Assert.assertFalse(game.isTheGameComplete());
    }

    @Test
    public void GameShouldListIfTheGameHasConcludedAndWinner() {
        game = createGameWithoutException(gameConfig);
        int[] movesPlayed = {0, 1, 3, 2, 6};
        // Moves played by computer => 1, 2

        performGameTurns(movesPlayed);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "Player X has won");
    }

    @Test
    public void GameShouldListIfTheGameHasConcludedAndAComputerWon() {
        game = createGameWithoutException(gameConfig);
        int[] movesPlayed = {0, 1, 3, 2, 5, 4, 8, 7};
        // Moves played by computer => 1, 2, 4, 7

        performGameTurns(movesPlayed);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "Player O has won");
    }

    @Test
    public void GameShouldNotListWinnerIfTheGameConcludedAsATie() {
        game = createGameWithoutException(gameConfig);
        int[] movesPlayed = {4, 0, 1, 2, 6, 3, 5, 7, 8};
        // Moves played by computer => 0, 2, 3, 7

        performGameTurns(movesPlayed);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "It's a tie");
    }

    @Test
    public void getFirstAvailableMoveReturnsTheFirstAvailableMoveFromTheBoard() {
        game = createGameWithoutException(gameConfig);
        int[] movesPlayed = {4, 0};
        // Moves played by computer => 0

        performGameTurns(movesPlayed);

        Assert.assertEquals(game.getFirstAvailableMove(), 1);
    }

    private void performGameTurns(int[] moves) {
        for(int i = 0; i < moves.length; i++) {
            int location = moves[i];
            testPlayer = i % 2 == 0 ? new TestPlayer("X", new int[] {location}) : new TestPlayer("O", new int[] {location});
            game.performTurn(testPlayer);
        }
    }

    private void performHumanGameTurns(int[] moves) {
        for(int i = 0; i < moves.length; i++) {
            int location = moves[i];
            testPlayer = i % 2 == 0 ? new TestPlayer("X", new int[] {location}) : new TestPlayer("O", new int[] {location});
            game.performTurn(testPlayer);
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
