package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class ConsoleClientTest {
    ByteArrayOutputStream terminal = new ByteArrayOutputStream();
    PrintStream outputStream = new PrintStream(terminal);

    ConsoleClient console;

    private void setInput(String value) {
        InputStream in = new ByteArrayInputStream(value.getBytes());
        System.setIn(in);
    }

    @Before
    public void setup() {
        System.setOut(outputStream);
    }

    @Test
    public void consoleRunnerAsksIfItsAHumanVSHuman() {
        String input = "y";
        setInput(input);
        console = new ConsoleClient();

        Assert.assertTrue(terminal.toString().contains("Is this a Human vs Human Game? [y/n]"));
        Assert.assertFalse(console.getGame().isTheGameComplete());

    }

    @Test
    public void consoleSetsUpGameWithDefaultBoardSizeAnd2HumanPlayers() {
        String board;
        String input = "y";
        setInput(input);
        console = new ConsoleClient();
        board = console.getGame().getBoard();
        System.out.println(board.length());

        Assert.assertEquals(board.length(), 9);
        Assert.assertFalse(console.getGame().checkPlayer1AIStatus());
        Assert.assertFalse(console.getGame().checkPlayer2AIStatus());
    }

    @Test
    public void consoleRendersDefaultBoardAfterSelectingGameType() {
        String input = "n";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("123\n---\n456\n---\n789"));
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
