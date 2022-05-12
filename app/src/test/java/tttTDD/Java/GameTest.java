package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}
