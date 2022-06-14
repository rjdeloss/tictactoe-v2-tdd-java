package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

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
    public void consoleSetsUpGameWithDefaultBoardSizeShouldPromptPlayerMultipleTimes() throws Exception {
        String input = "-1 \n three \n -1 \n 3 1 2";
        setInput(input);
        console = new ConsoleClient();

        Assert.assertTrue(terminal.toString().contains("Can't have the Universe implode on us. Try a larger number:"));
        Assert.assertTrue(terminal.toString().contains("Stop it. Enter a number as an input:"));
    }

    @Test
    public void playerInputShouldPromptAReentryIfTheInputIsNotAValidInput() throws Exception{
        String input = "three \n 1 \n 2 \n 3 3 2";
        setInput(input);
        console = new ConsoleClient();

        Assert.assertTrue(terminal.toString().contains("Stop it. Enter a number as an input:"));

    }

    @Test
    public void consoleRunnerAskForBoardSizeInput() {
        String input = "4 3 2 1 5 2 6 3 7 4";
        setInput(input);
        console = createConsoleWithoutException();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Please enter a board size number:"));
        assertThat(terminal.toString(), containsString(" 1 | 2 | 3 | 4\n---+---+---+---\n 5 | 6 | 7 | 8\n---+---+---+---\n 9 |10 |11 |12\n---+---+---+---\n13 |14 |15 |16"));
    }

    @Test
    public void consoleRendersDefaultBoardAfterSelectingGameType() {
        String input = "3 3 2 1 3 2 5 4 7";
        setInput(input);
        console = createConsoleWithoutException();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("1 |2 |3\n--+--+--\n4 |5 |6\n--+--+--\n7 |8 |9"));
    }

    @Test
    public void consoleLoopShouldPromptCurrentPlayerToPerformAMove() {
        String input = "3 1 2 1 4 7";
        setInput(input);
        console = createConsoleWithoutException();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Make your move:"));
    }

    @Test
    public void boardShouldUpdateWithPlayerInput() {
        String input = "3 1 2 1 3 2 5 4 7";
        setInput(input);
        console = createConsoleWithoutException();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("1 |2 |3\n--+--+--\n4 |5 |6\n--+--+--\n7 |8 |9"));
        Assert.assertTrue(terminal.toString().contains("Player X has made a move on space 1"));
    }

    @Test
    public void consoleLoopShouldPromptCurrentPlayerToPerformAMoveAgainIfCellIsTaken() throws Exception {
        String input = "3 1 2 1 1 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Oops... incorrect move. Please try again"));
    }

    @Test
    public void consoleShouldRenderAMessageWithTheWinnerOfTheGame() {
        String input = "3 1 2 1 4 7";
        setInput(input);
        console = createConsoleWithoutException();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Player X has won"));
    }

    @Test
    public void consoleShouldRenderAMessageWhenGameEndsInATie() {
        String input = "3 1 2 5 1 2 3 7 4 6 8 9";
        setInput(input);
        console = createConsoleWithoutException();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("It's a tie"));
    }

    private ConsoleClient createConsoleWithoutException() {
        try {
            return new ConsoleClient();
        }

        catch (Exception e){
            return null;
        }
    }
}
