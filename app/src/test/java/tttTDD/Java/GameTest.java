package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tttTDD.Java.Interfaces.Player;

import javax.swing.*;
import java.lang.module.Configuration;

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
        Assert.assertEquals(game.board.getSpace(playerInput), game.currentPlayer.getMarker());
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
}
