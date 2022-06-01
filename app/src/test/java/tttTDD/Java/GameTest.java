package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tttTDD.Java.Interfaces.Player;


public class GameTest {
    Game game;
    GameConfiguration gameConfig;
    Player player1;
    Player player2;

    @Before
    public void setup() {
        gameConfig = new GameConfiguration(3, true);
        player1 = new Human("X");
        player2 = new Computer("O");
    }

    @Test
    public void PlayerShouldUpdateBoardWithinTheGame3x3() {
        // Arrange
        int playerInput = 3;
        game = new Game(gameConfig);

        // Act
        game.performTurn(playerInput);

        // Assert
        Assert.assertNotEquals(game.performTurn(playerInput), false);
    }

    @Test
    public void PerformTurnShouldReturnFalseWhenASpaceIsTaken() {
        game = new Game(gameConfig);
        int[] humanMoves = {0, 1, 0};
        // Moves played by computer => 1

        performGameTurns(humanMoves);

        Assert.assertFalse(game.performTurn(humanMoves[1]));
    }

    @Test
    public void GameShouldSwapPlayersAfterSuccessfulMoveOnTheBoard() {
        int playerInput = 3;
        game = new Game(gameConfig);

        // Act
        Player playerMakingMove = game.getCurrentPlayer();
        game.performTurn(playerInput);
        Player nextPlayer = game.getCurrentPlayer();

        // Assert
        Assert.assertNotEquals(playerMakingMove.getMarker(), nextPlayer.getMarker());
    }

    @Test
    public void GameShouldGenerateHumanPlayerAsFirstPlayerIfConfigFlagIsTrue(){
        game = new Game(gameConfig);

        Assert.assertEquals(game.getPlayer1Marker(), "X");
        Assert.assertFalse(game.checkPlayer1AIStatus());
    }

    @Test
    public void GameShouldGenerateComputerPlayerAsSecondPlayerIfConfigFlagIsTrue(){
        game = new Game(gameConfig);

        Assert.assertTrue(game.checkPlayer2AIStatus());
    }

    @Test
    public void ComputerShouldTakeTheFirstAvailableMoveAtLocation0() {
        game = new Game(gameConfig);
        int[] playerInputs = {3, 2};

        performGameTurns(playerInputs);

        Assert.assertEquals(game.getBoardSpace(0), game.getPlayer2Marker());
    }

    @Test
    public void ComputerShouldTakeTheFirstAvailableMoveAtLocation4() {
        game = new Game(gameConfig);
        int[] movesPlayed = {3, 5, 2, 7, 6, 9};
        // Moves played by computer => 5, 7, 9

        performGameTurns(movesPlayed);

        Assert.assertEquals(game.getBoardSpace(4), game.getPlayer2Marker());
    }

    @Test
    public void GameShouldProvideFirstAvailableMoveForComputerPlayerFromTheBoard() {
        game = new Game(gameConfig);
        int playerInput = 0;

        game.performTurn(playerInput);

        Assert.assertEquals(game.getBoardFirstAvailableMove(), 1);
    }

    @Test
    public void ChangeGameStateGameCompletedToTrueIfWinningSetFound() {
        game = new Game(gameConfig);
        int[] movesPlayed = {0, 1, 3, 2, 6};
        // Moves played by computer => 1, 2

        performGameTurns(movesPlayed);

        Assert.assertTrue(game.isTheGameComplete());
    }

    @Test
    public void ChangeGameStateGameCompletedToFalseIfWinningSetNotFound() {
        game = new Game(gameConfig);
        int[] movesPlayed = {8, 1, 3, 2};
        // Moves played by computer => 1, 2

        performGameTurns(movesPlayed);

        Assert.assertFalse(game.isTheGameComplete());
    }

    @Test
    public void GameShouldListIfTheGameHasConcludedAndWinner() {
        game = new Game(gameConfig);
        int[] movesPlayed = {0, 1, 3, 2, 6};
        // Moves played by computer => 1, 2

        performGameTurns(movesPlayed);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "Player X has won");
    }

    @Test
    public void GameShouldListIfTheGameHasConcludedAndAComputerWon() {
        game = new Game(gameConfig);
        int[] movesPlayed = {0, 1, 3, 2, 5, 4, 8, 7};
        // Moves played by computer => 1, 2, 4, 7

        performGameTurns(movesPlayed);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "Player O has won");
    }

    @Test
    public void GameShouldNotListWinnerIfTheGameConcludedAsATie() {
        game = new Game(gameConfig);
        int[] movesPlayed = {4, 0, 1, 2, 6, 3, 5, 7, 8};
        // Moves played by computer => 0, 2, 3, 7

        performGameTurns(movesPlayed);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "It's a tie");
    }

    @Test
    public void getFirstAvailableMoveReturnsTheFirstAvailableMoveFromTheBoard() {
        game = new Game(gameConfig);
        int[] movesPlayed = {4, 0};
        // Moves played by computer => 0

        performGameTurns(movesPlayed);

        Assert.assertEquals(game.getFirstAvailableMove(), 1);
    }

    private void performGameTurns(int[] moves) {
        for(int location : moves) {
            game.performTurn(location);
        }
    }
}
