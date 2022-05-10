package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest{
    Human human;
    Computer computer;

    @Before
    public void setup() {

    }

    @Test
    public void createInstanceOfAPlayer() {
        human = new Human("X");
        Assert.assertEquals(human.getMarker(), "X");
    }

    @Test
    public void verifyTheCorrectMarkerHasBeenAssigned() {
        human = new Human("S");
        Assert.assertNotEquals(human.getMarker(), "X");
        Assert.assertEquals(human.getMarker(), "S");
    }

    @Test
    public void verifyThePlayerBeingHuman() {
        human = new Human("O");
        Assert.assertEquals(human.isComputer(), false);
    }

    @Test
    public void verifyThePlayerBeingAComputer() {
        computer = new Computer("X");
        Assert.assertEquals(computer.isComputer(), true);
    }
}