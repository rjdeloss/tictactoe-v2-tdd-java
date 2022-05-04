package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest{
    Player human;
    Player computer;

    @Before
    public void setup() {

    }

    @Test
    public void createInstanceOfHumanPlayer() {
        human = new Player("X", false);
        Assert.assertEquals(human.getMarker(), "X");
    }

    @Test
    public void verifyTheCorrectMarkerHasBeenAssigned() {
        human = new Player("S", false);
        Assert.assertNotEquals(human.getMarker(), "X");
    }

    @Test
    public void verifyThePlayerBeingHuman() {
        human = new Player("O", false);
        Assert.assertEquals(human.isComputer(), false);
    }

    @Test
    public void verifyThePlayerBeingAComputer() {
        computer = new Player("X", true);
        Assert.assertEquals(computer.isComputer(), true);
    }
}