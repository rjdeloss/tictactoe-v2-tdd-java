package tttTDD.Java;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComputerTest {



    @Test
    public void ComputerShouldTakeTheFirstAvailableMoveAtLocation0() {
        // Arrange
        Computer computerPlayer = new Computer("O");
        Board board = new Board();

        //Act
        int result = computerPlayer.move(board);
        //Assert
        Assert.assertEquals(result, 0);
    }

    @Test
    public void ComputerShouldNotTakeASpaceIfTheSpaceIsNotAvailable() {
        Computer computerPlayer = new Computer("O");

        Board board = new Board();
        board.updateBoard(0, "X");

        int result = computerPlayer.move(board);

        Assert.assertNotEquals(result, 0);
    }
}
