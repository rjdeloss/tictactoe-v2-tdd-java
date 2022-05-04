package tttTDD.Java;


import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    Board board;

    @Test
    public void getBoardInstanceWithCorrectLength() {
        board = new Board();

        Assert.assertEquals(board.getBoard().length, 9);
    }

    @Test
    public void getBoardSpace() {
        board = new Board();

        Assert.assertEquals(board.getSpace(5), "6");
        Assert.assertNotEquals(board.getSpace(5), "5");
        Assert.assertEquals(board.getSpace(3), "4");
        Assert.assertEquals(board.getSpace(8), "9");
    }

    @Test
    public void updateBoardSpaceWithNewValue() {
        board = new Board();
        board.updateBoard(2, "X");

        Assert.assertEquals(board.getSpace(2), "X");
    }

}
