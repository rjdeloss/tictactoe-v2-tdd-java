package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ConsoleRunnerTest {
    ByteArrayOutputStream testConsole = new ByteArrayOutputStream();
    PrintStream outputStream = new PrintStream(testConsole);

    ConsoleRunner console;
    GameConfiguration gameConfig;

    private void setInput(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @Before
    public void setup() {
        System.setOut(outputStream);
    }

    @Test
    public void consoleRunnerAsksQuestionsToUser() {
        setInput("y");
        console = new ConsoleRunner();

        Assert.assertTrue(testConsole.toString().contains("Is this a Human vs Human Game? [y/n]"));
        Assert.assertFalse(console.getGame().isTheGameComplete());
        Assert.assertFalse(console.getGame().checkPlayer1AIStatus());
        Assert.assertFalse(console.getGame().checkPlayer2AIStatus());

    }

    // 2nd test to check for real input. Force scanner on here

    // 3rd test to check for correct setup of the game

    //4th check for render board

//    @Test
//    public void consoleShouldReceiveInputToCreateGameConfiguration() {
//        String input = "3 true";
//        setInput( input );
//        console = new ConsoleRunner(gameConfig);
//
//        console.createGameConfiguration();
//        //should print out an empty board
//    }
}
