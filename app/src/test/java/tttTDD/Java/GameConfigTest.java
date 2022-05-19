package tttTDD.Java;

import org.junit.Assert;
import org.junit.Test;

public class GameConfigTest {
    GameConfiguration gameConfig;

    @Test
    public void createGameConfigurationObject() {
        gameConfig = new GameConfiguration(3, false);

        Assert.assertEquals(gameConfig.getBoardSizeConfiguration(), 3);
        Assert.assertEquals(gameConfig.isComputerPlaying(), false);
    }
}
