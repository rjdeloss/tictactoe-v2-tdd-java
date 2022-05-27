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
        String input = "y 1 3 2 5 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("1|2|3\n-+-+-\n4|5|6\n-+-+-\n7|8|9"));
    }

    @Test
    public void consoleLoopShouldPromptCurrentPlayerToPerformAMove() {
        String input = "n 1 1 3 2 5 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Make your move:"));
    }

    @Test
    public void boardShouldUpdateWithPlayerInput() {
        String input = "y 1 3 2 5 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("X|2|3\n-+-+-\n4|5|6\n-+-+-\n7|8|9"));
    }

    @Test
    public void consoleLoopShouldPromptCurrentPlayerToPerformAMoveAgainIfCellIsTaken() {
        String input = "n 1 1 3 2 5 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Oops... incorrect move. Please try again"));
    }

    @Test
    public void consoleShouldRenderAMessageWithTheWinnerOfTheGame() {
        String input = "n 1 3 2 5 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Player X has won"));
    }

    @Test
    public void consoleShouldRenderAMessageWhenGameEndsInATie() {
        String input = "y 5 1 2 3 7 4 6 8 9";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("It's a tie"));
    }
}
