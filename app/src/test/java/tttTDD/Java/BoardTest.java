package tttTDD.Java;


import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    Board board;

    @Test
    public void getBoardInstanceWithCorrectDefaultLengthOf3() {
        board = new Board(3);

        Assert.assertEquals(board.getBoard().length, 9);
    }

    @Test
    public void getBoardInstanceWithResizableBoard() {
        // Arrange

        // Act
        board = new Board(4);


        // Assert
        Assert.assertEquals(board.getBoard().length, 16);
        Assert.assertEquals(board.getSpace(0), "1");
        Assert.assertEquals(board.getSpace(15), "16");
    }

    @Test
    public void getBoardSpace() {
        board = new Board(3);

        Assert.assertEquals(board.getSpace(5), "6");
        Assert.assertNotEquals(board.getSpace(5), "5");
        Assert.assertEquals(board.getSpace(3), "4");
        Assert.assertEquals(board.getSpace(8), "9");
    }

    @Test
    public void updateBoardSpaceWithNewValue() throws Exception {
        board = new Board();
        board.updateBoard(2, "X");

        Assert.assertEquals(board.getSpace(2), "X");
    }

    @Test(expected = Exception.class)
    public void updatingTheSameSpaceWillResultInAnErrorException() throws Exception {
        board = new Board();
        board.updateBoard(3, "X");
        board.updateBoard(3, "O");
    }

    // for each of these horizontal, vertical, top-to-bottom diagonal, bottom-to-top diagonal
    // validate player win on 3x3
    // validate player win on 4x4
    // validate player win on 100x100
}
