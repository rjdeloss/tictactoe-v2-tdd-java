package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest{
    Player human;

    @Before
    public void setup() {

    }

    @Test
    public void createInstanceOfHumanPlayer() {
        human = new Player("X");
        Assert.assertEquals(human.getMarker(), "X");
    }
}