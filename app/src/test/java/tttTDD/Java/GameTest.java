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
        int humanInput1 = 0;
        int computerInput1 = 1;
        int humanInput2 = 0;

        game.performTurn(humanInput1);
        game.performTurn(computerInput1);

        Assert.assertFalse(game.performTurn(humanInput2));
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
    public void GameShouldReturnAStringWithAllTheBoardValues() {
        game = new Game(gameConfig);

        Assert.assertEquals(game.toString(), "123456789");
    }

    @Test
    public void ComputerShouldTakeTheFirstAvailableMoveAtLocation0() {
        int playerInput = 3;
        game = new Game(gameConfig);

        game.performTurn(playerInput);
        game.performTurn(2);

        Assert.assertEquals(game.getBoardSpace(0), game.getPlayer2Marker());
    }

    @Test
    public void ComputerShouldTakeTheFirstAvailableMoveAtLocation4() {
        int playerInput1 = 3;
        int playerInput2 = 2;
        int playerInput3 = 6;
        game = new Game(gameConfig);

        game.performTurn(playerInput1);
        game.performTurn(2);
        game.performTurn(playerInput2);
        game.performTurn(7);
        game.performTurn(playerInput3);
        game.performTurn(9);

        Assert.assertEquals(game.getBoardSpace(4), game.getPlayer2Marker());
    }

    @Test
    public void GameShouldProvideFirstAvailableMoveForComputerPlayerFromTheBoard() {
        int playerInput = 0;
        game = new Game(gameConfig);

        game.performTurn(playerInput);

        Assert.assertEquals(game.getBoardFirstAvailableMove(), 1);
    }

    @Test
    public void ChangeGameStateGameCompletedToTrueIfWinningSetFound() {
        game = new Game(gameConfig);

        int humanInput1 = 0;
        int computerInput1 = 1;
        int humanInput2 = 3;
        int computerInput2 = 2;
        int humanInput3 = 6;

        game.performTurn(humanInput1);
        game.performTurn(computerInput1);
        game.performTurn(humanInput2);
        game.performTurn(computerInput2);
        game.performTurn(humanInput3);

        Assert.assertTrue(game.isTheGameComplete());
    }

    @Test
    public void ChangeGameStateGameCompletedToFalseIfWinningSetNotFound() {
        game = new Game(gameConfig);

        int humanInput1 = 8;
        int computerInput1 = 1;
        int humanInput2 = 3;
        int computerInput2 = 2;

        game.performTurn(humanInput1);
        game.performTurn(computerInput1);
        game.performTurn(humanInput2);
        game.performTurn(computerInput2);

        Assert.assertFalse(game.isTheGameComplete());
    }

    @Test
    public void GameShouldListIfTheGameHasConcludedAndWinner() {
        game = new Game(gameConfig);

        int humanInput1 = 0;
        int computerInput1 = 1;
        int humanInput2 = 3;
        int computerInput2 = 2;
        int humanInput3 = 6;

        game.performTurn(humanInput1);
        game.performTurn(computerInput1);
        game.performTurn(humanInput2);
        game.performTurn(computerInput2);
        game.performTurn(humanInput3);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "Player X has won");
    }

    @Test
    public void GameShouldListIfTheGameHasConcludedAndAComputerWon() {
        game = new Game(gameConfig);

        int humanInput1 = 0;
        int computerInput1 = 1;
        int humanInput2 = 3;
        int computerInput2 = 2;
        int humanInput3 = 5;
        int computerInput3 = 4;
        int humanInput4 = 8;
        int computerInput4 = 7;

        game.performTurn(humanInput1);
        game.performTurn(computerInput1);
        game.performTurn(humanInput2);
        game.performTurn(computerInput2);
        game.performTurn(humanInput3);
        game.performTurn(computerInput3);
        game.performTurn(humanInput4);
        game.performTurn(computerInput4);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "Player O has won");
    }

    @Test
    public void GameShouldNotListWinnerIfTheGameConcludedAsATie() {
        game = new Game(gameConfig);

        int humanInput1 = 4;
        int computerInput1 = 0;
        int humanInput2 = 1;
        int computerInput2 = 2;
        int humanInput3 = 6;
        int computerInput3 = 3;
        int humanInput4 = 5;
        int computerInput4 = 7;
        int humanInput5 = 8;

        game.performTurn(humanInput1);
        game.performTurn(computerInput1);
        game.performTurn(humanInput2);
        game.performTurn(computerInput2);
        game.performTurn(humanInput3);
        game.performTurn(computerInput3);
        game.performTurn(humanInput4);
        game.performTurn(computerInput4);
        game.performTurn(humanInput5);

        Assert.assertTrue(game.isTheGameComplete());
        Assert.assertEquals(game.wonBy(), "It's a tie");
    }

    @Test
    public void getFirstAvailableMoveReturnsTheFirstAvailableMoveFromTheBoard() {
        game = new Game(gameConfig);
        int humanInput1 = 4;
        int computerInput1 = 0;

        game.performTurn(humanInput1);
        game.performTurn(computerInput1);

        Assert.assertEquals(game.getFirstAvailableMove(), 1);
    }
}
