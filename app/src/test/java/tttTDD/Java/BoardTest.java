package tttTDD.Java;


import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    Board board;

    @Test
    public void getBoardInstance() {
        board = new Board();
        Assert.assertEquals(board.getBoard().length, 9);
    }

}
