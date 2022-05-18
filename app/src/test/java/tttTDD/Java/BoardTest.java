package tttTDD.Java;


import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    Board board;

    @Test
    public void getBoardInstanceWithCorrectDefaultLengthOf3() {
        board = new Board(3);

        Assert.assertEquals(board.getSpace(0), "1");
        Assert.assertEquals(board.getSpace(8), "9");
    }

    @Test
    public void getBoardInstanceWithResizableBoard() {
        board = new Board(4);

        // Assert
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
    public void checkIfLocationIsOutOfBounds() {
        board = new Board(3);

        Assert.assertNull(board.getSpace(9));
        Assert.assertNull(board.getSpace(16));
    }

    @Test
    public void updateBoardSpaceWithNewValue(){
        board = new Board();

        Assert.assertTrue(board.updateBoard(2, "X"));
        Assert.assertEquals(board.getSpace(2), "X");
    }

    @Test
    public void updatingTheSameSpaceWillResultInAnErrorException() {
        board = new Board();
        board.updateBoard(3, "X");

        Assert.assertFalse(board.updateBoard(3, "O"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf3x3Board() {
        board = new Board();
        board.updateBoard(0, "X");
        board.updateBoard(1, "X");
        board.updateBoard(2, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnMiddleRowOf3x3Board() {
        board = new Board();
        board.updateBoard(3, "X");
        board.updateBoard(4, "X");
        board.updateBoard(5, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnBottomRowOf3x3Board() {
        board = new Board();
        board.updateBoard(6, "X");
        board.updateBoard(7, "X");
        board.updateBoard(8, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftColumnOf3x3Board() {
        board = new Board();
        board.updateBoard(0, "X");
        board.updateBoard(3, "X");
        board.updateBoard(6, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnMiddleColumnOf3x3Board() {
        board = new Board();
        board.updateBoard(0, "X");
        board.updateBoard(3, "X");
        board.updateBoard(6, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnRightColumnOf3x3Board() {
        board = new Board();
        board.updateBoard(2, "X");
        board.updateBoard(5, "X");
        board.updateBoard(8, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftDiagonalOf3x3Board() {
        board = new Board();
        board.updateBoard(0, "X");
        board.updateBoard(4, "X");
        board.updateBoard(8, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnRightDiagonalOf3x3Board() {
        board = new Board();
        board.updateBoard(2, "X");
        board.updateBoard(4, "X");
        board.updateBoard(6, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf5x5Board() {
        board = new Board(5);
        board.updateBoard(0, "X");
        board.updateBoard(1, "X");
        board.updateBoard(2, "X");
        board.updateBoard(3, "X");
        board.updateBoard(4, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf2x2Board() {
        board = new Board(2);

        board.updateBoard(0, "X");
        board.updateBoard(1, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftDiagonalOf6x6Board() {
        board = new Board(6);
        board.updateBoard(0, "X");
        board.updateBoard(7, "X");
        board.updateBoard(14, "X");
        board.updateBoard(21, "X");
        board.updateBoard(28, "X");
        board.updateBoard(35, "X");


        Assert.assertTrue(board.hasWinningSet("X"));
    }
}
