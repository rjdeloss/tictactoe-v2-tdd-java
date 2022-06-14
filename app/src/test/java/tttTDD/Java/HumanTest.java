package tttTDD.Java;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class HumanTest {
    Human human;
    Board board = null;
    Scanner sc = new Scanner(System.in);


    private void setInput(String value) {
        InputStream in = new ByteArrayInputStream(value.getBytes());
        System.setIn(in);
    }

    @Test
    public void createInstanceOfAPlayer() {
        human = new Human("X", sc);
        Assert.assertEquals(human.getMarker(), "X");
    }

    @Test
    public void verifyTheCorrectMarkerHasBeenAssigned() {
        human = new Human("S", sc);
        Assert.assertNotEquals(human.getMarker(), "X");
        Assert.assertEquals(human.getMarker(), "S");
    }

    @Test
    public void verifyThePlayerBeingHuman() {
        human = new Human("O", sc);
        Assert.assertFalse(human.isComputer());
    }

    @Test
    public void humanCanGetABoardIndex() {
        String moves = "3 \n";
        setInput(moves);
        Scanner sc = new Scanner(System.in);
        human = new Human("X", sc);

        int result = human.move(board);

        Assert.assertEquals(result, 2);
    }

    @Test
    public void humanCantEnterWordsAsInput() {
        String input = "applesauce\n canttakethisinput\n 4\n";
        setInput(input);
        Scanner sc = new Scanner(System.in);
        human = new Human("O", sc);

        int result = human.move(board);

        Assert.assertEquals(result, 3);
    }
}