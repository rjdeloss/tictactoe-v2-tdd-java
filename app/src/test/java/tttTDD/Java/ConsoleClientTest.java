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
    public void consoleAsksIfItsAHumanVSHuman() {
        String input = "y 3";
        setInput(input);
        console = new ConsoleClient();

        Assert.assertTrue(terminal.toString().contains("Is this a Human vs Human Game? [y/n]"));
        Assert.assertFalse(console.getGame().isTheGameComplete());

    }

    @Test
    public void consoleDefaultsToHumanVsComputerWithAnyOtherAnswer() {
        String input = "n 3";
        setInput(input);
        console = new ConsoleClient();

        Assert.assertFalse(console.getGame().checkPlayer1AIStatus());
        Assert.assertTrue(console.getGame().checkPlayer2AIStatus());
    }

    @Test
    public void consoleSetsUpGameWithDefaultBoardSizeAnd2HumanPlayers() {
        String input = "y 3";
        setInput(input);
        console = new ConsoleClient();

        Assert.assertFalse(console.getGame().checkPlayer1AIStatus());
        Assert.assertFalse(console.getGame().checkPlayer2AIStatus());
    }

    @Test
    public void playerInputShouldPromptAReentryIfTheInputIsNotAValidInput() {
        String input = "y three \n 3";
        setInput(input);
        console = new ConsoleClient();

        Assert.assertTrue(terminal.toString().contains("Stop it. Enter a number as an input:"));

    }

    @Test
    public void consoleRunnerAskForBoardSizeInput() {
        String input = "y 4 1 5 2 6 3 7 4";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Please enter a board size number:"));
<<<<<<< Updated upstream
        assertThat(terminal.toString(), containsString(" 1 | 2 | 3 | 4\n---+---+---+---\n 5 | 6 | 7 | 8\n---+---+---+---\n 9 |10 |11 |12\n---+---+---+---\n13 |14 |15 |16"));
=======
//        assertThat(terminal.toString(), containsString("1 2 3 4 \n5 6 7 8 \n9 10 11 12 \n13 14 15 16 "));
>>>>>>> Stashed changes
    }

    @Test
    public void consoleRendersDefaultBoardAfterSelectingGameType() {
        String input = "y 3 1 3 2 5 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

<<<<<<< Updated upstream
        Assert.assertTrue(terminal.toString().contains("1 |2 |3\n--+--+--\n4 |5 |6\n--+--+--\n7 |8 |9"));
=======
//        Assert.assertTrue(terminal.toString().contains("1 2 3 \n4 5 6 \n7 8 9 "));
>>>>>>> Stashed changes
    }

    @Test
    public void consoleLoopShouldPromptCurrentPlayerToPerformAMove() {
        String input = "n 3 1 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Make your move:"));
    }

    @Test
    public void boardShouldUpdateWithPlayerInput() {
        String input = "y 3 1 3 2 5 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

<<<<<<< Updated upstream
        Assert.assertTrue(terminal.toString().contains("1 |2 |3\n--+--+--\n4 |5 |6\n--+--+--\n7 |8 |9"));
=======
//        Assert.assertTrue(terminal.toString().contains("X 2 3 \n4 5 6 \n7 8 9 "));
>>>>>>> Stashed changes
        Assert.assertTrue(terminal.toString().contains("Player X has made a move on space 1"));
    }

    @Test
    public void consoleLoopShouldPromptCurrentPlayerToPerformAMoveAgainIfCellIsTaken() {
        String input = "n 3 1 1 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Oops... incorrect move. Please try again"));
    }

    @Test
    public void consoleShouldRenderAMessageWithTheWinnerOfTheGame() {
        String input = "n 3 1 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("Player X has won"));
    }

    @Test
    public void consoleShouldRenderAMessageWhenGameEndsInATie() {
        String input = "y 3 5 1 2 3 7 4 6 8 9";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        Assert.assertTrue(terminal.toString().contains("It's a tie"));
    }

    @Test
    public void consoleShouldDisplayColumnsOnA3x3Board() {
        String input = "y 3 1 3 2 5 4 7";
        setInput(input);
        console = new ConsoleClient();
        console.startGame();

        String expectedBoardRendered =
                "1 | 2 | 3\n" +
                "4 | 5 | 6\n" +
                "7 | 8 | 9";

//        Assert.assertTrue(terminal.toString().contains(expectedBoardRendered)
        assertThat(terminal.toString(), containsString(expectedBoardRendered));
    }
}
