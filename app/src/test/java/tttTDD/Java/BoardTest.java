package tttTDD.Java;


import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    Board board;

    @Test
    public void getBoardInstanceWithATotalOf9Spaces() {
        board = new Board();

        Assert.assertEquals(board.getSpace(0), "1");
        Assert.assertEquals(board.getSpace(8), "9");
        Assert.assertNull(board.getSpace(-1));
        Assert.assertNull(board.getSpace(9));
        Assert.assertNull(board.getSpace(100));
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
        board = new Board();

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
        int[] moves = {0, 1, 2};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnMiddleRowOf3x3Board() {
        board = new Board();
        int[] moves = {3, 4, 5};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnBottomRowOf3x3Board() {
        board = new Board();
        int[] moves = {6, 7, 8};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftColumnOf3x3Board() {
        board = new Board();
        int[] moves = {0, 3, 6};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnMiddleColumnOf3x3Board() {
        board = new Board();
        int[] moves = {1, 4, 7};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnRightColumnOf3x3Board() {
        board = new Board();
        int[] moves = {2, 5, 8};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftDiagonalOf3x3Board() {
        board = new Board();
        int[] moves = {0, 4, 8};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnRightDiagonalOf3x3Board() {
        board = new Board();
        int[] moves = {2, 4, 6};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf5x5Board() {
        board = new Board(5);
        int[] moves = {0, 1, 2, 3, 4};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf2x2Board() {
        board = new Board(2);
        int[] moves = {0, 1};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftDiagonalOf6x6Board() {
        board = new Board(6);
        int[] moves = {0, 7, 14, 21, 28, 35};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheBoardHasNoMoreMoves() {
        board = new Board();
        int[] moves = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        String[] markers = {"X", "O", "X", "O", "O", "X", "O", "X", "X"};

        updateCellsOnBoard(moves, markers);

        Assert.assertTrue(board.hasNoMoreMoves());
    }

    @Test
    public void checkIfTheBoardHasMoves() {
        board = new Board();
        int[] moves = {0, 1, 2, 3, 4};
        String[] markers = {"X", "O", "X", "O", "O"};

        updateCellsOnBoard(moves, markers);

        Assert.assertFalse(board.hasNoMoreMoves());
    }

    private void updateCellsOnBoard(int[] locations, String[] markers) {
        for(int i = 0; i < locations.length; i++) {
            int location = locations[i];
            String marker = markers[i];

            board.updateBoard(location, marker);
        }
    }

    private void updateCellsOnBoard(int[] locations, String marker) {
        for(int i = 0; i < locations.length; i++) {
            int location = locations[i];

            board.updateBoard(location, marker);
        }
    }
}
