package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tttTDD.Java.Interfaces.Player;

public class GameTest {
    Game game;
    GameConfiguration gameConfig;

    @Before
    public void setup() {
        gameConfig = new GameConfiguration(3, true);
    }

    @Test
    public void PlayerShouldUpdateBoardWithinTheGame3x3() {
        // Arrange
        int playerInput = 3;
        game = new Game(gameConfig);

        // Act
        game.performMove(playerInput);

        // Assert
        Assert.assertEquals(game.board.getSpace(playerInput), game.player1.getMarker());
    }

    @Test
    public void GameShouldSwapPlayersAfterSuccessfulMoveOnTheBoard() {
        int playerInput = 3;
        game = new Game(gameConfig);

        // Act
        Player playerMakingMove = game.currentPlayer;
        game.performMove(playerInput);

        // Assert
        Assert.assertNotEquals(playerMakingMove.getMarker(), game.currentPlayer.getMarker());
    }

    @Test
    public void GameShouldGenerateComputerPlayerAsSecondPlayerIfConfigFlagIsTrue(){
        game = new Game(gameConfig);

        Assert.assertTrue(game.player2.isComputer());
    }

    @Test
    public void ComputerShouldTakeTheFirstAvailableMoveAtLocation0() {
        int playerInput = 3;
        game = new Game(gameConfig);

        game.performMove(playerInput);
        game.performMove(2);

        Assert.assertEquals(game.board.getSpace(0), game.player2.getMarker());
    }

    @Test
    public void ComputerShouldTakeTheFirstAvailableMoveAtLocation4() {
        int playerInput1 = 3;
        int playerInput2 = 2;
        int playerInput3 = 6;
        game = new Game(gameConfig);

        game.performMove(playerInput1);
        game.performMove(2);
        game.performMove(playerInput2);
        game.performMove(7);
        game.performMove(playerInput3);
        game.performMove(9);

        Assert.assertEquals(game.board.getSpace(4), game.player2.getMarker());
    }

    @Test
    public void GameShouldProvideFirstAvailableMoveForComputerPlayerFromTheBoard() {
        int playerInput = 0;
        game = new Game(gameConfig);

        game.performMove(playerInput);

        Assert.assertEquals(game.board.getFirstAvailableMove(), 1);
    }

//    @Test
//    public void ChangeGameStateGameCompletedToTrueIfWinningSetFound() {
//        game = new Game(gameConfig);
//
//        int humanInput1 = 0;
//        int computerInput1 = 1;
//        int humanInput2 = 3;
//        int computerInput2 = 2;
//        int humanInput3 = 6;
//
//        game.performMove(humanInput1);
//        game.performMove(computerInput1);
//        game.performMove(humanInput2);
//        game.performMove(computerInput2);
//        game.performMove(humanInput3);
//
//        Assert.assertTrue(game.gameCompleted);
//    }

//    @Test
//    public void ChangeGameStateGameCompletedToFalseIfWinningSetNotFound() {
//        game = new Game(gameConfig);
//
//        int humanInput1 = 8;
//        int computerInput1 = 1;
//        int humanInput2 = 3;
//        int computerInput2 = 2;
//
//        game.performMove(humanInput1);
//        game.performMove(computerInput1);
//        game.performMove(humanInput2);
//        game.performMove(computerInput2);
//
//        Assert.assertFalse(game.gameCompleted);
//    }

//    @Test
//    public void GameShouldListIfTheGameHasConcludedAndWinner() {
//        game = new Game(gameConfig);
//
//        int humanInput1 = 0;
//        int computerInput1 = 1;
//        int humanInput2 = 3;
//        int computerInput2 = 2;
//        int humanInput3 = 6;
//
//        game.performMove(humanInput1);
//        game.performMove(computerInput1);
//        game.performMove(humanInput2);
//        game.performMove(computerInput2);
//        game.performMove(humanInput3);
//
//        Assert.assertTrue(game.gameCompleted);
//        Assert.assertEquals(game.gameWinner, "Player X has won");
//    }
//
//    @Test
//    public void GameShouldListIfTheGameHasConcludedAndAComputerWon() {
//        game = new Game(gameConfig);
//
//        int humanInput1 = 0;
//        int computerInput1 = 1;
//        int humanInput2 = 3;
//        int computerInput2 = 2;
//        int humanInput3 = 5;
//        int computerInput3 = 4;
//        int humanInput4 = 8;
//        int computerInput4 = 7;
//
//        game.performMove(humanInput1);
//        game.performMove(computerInput1);
//        game.performMove(humanInput2);
//        game.performMove(computerInput2);
//        game.performMove(humanInput3);
//        game.performMove(computerInput3);
//        game.performMove(humanInput4);
//        game.performMove(computerInput4);
//
//        Assert.assertTrue(game.gameCompleted);
//        Assert.assertEquals(game.gameWinner, "Player O has won");
//    }
}
