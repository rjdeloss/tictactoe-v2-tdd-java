package tttTDD.Java;


import org.junit.Assert;
import org.junit.Test;

public class BoardTest {
    Board board;

    @Test
    public void getBoardInstanceWithResizableBoard() {
        board = createBoardWithoutExceptions(4);

        // Assert
        Assert.assertEquals(board.getSpace(0), "1");
        Assert.assertEquals(board.getSpace(15), "16");
    }

    @Test
    public void getBoardInstanceWithATotalOf9Spaces() {
        board = createBoardWithoutExceptions();

        Assert.assertEquals(board.getSpace(0), "1");
        Assert.assertEquals(board.getSpace(8), "9");
        Assert.assertNull(board.getSpace(-1));
        Assert.assertNull(board.getSpace(9));
        Assert.assertNull(board.getSpace(100));
    }

    @Test
    public void getBoardSpace() {
        board = createBoardWithoutExceptions();

        Assert.assertEquals(board.getSpace(5), "6");
        Assert.assertNotEquals(board.getSpace(5), "5");
        Assert.assertEquals(board.getSpace(3), "4");
        Assert.assertEquals(board.getSpace(8), "9");
    }

    @Test
    public void checkIfLocationIsOutOfBounds() {
        board = createBoardWithoutExceptions(3);

        Assert.assertNull(board.getSpace(9));
        Assert.assertNull(board.getSpace(16));
    }

    @Test
    public void updateBoardSpaceWithNewValue(){
        board = createBoardWithoutExceptions();

        Assert.assertTrue(board.updateBoard(2, "X"));
        Assert.assertEquals(board.getSpace(2), "X");
    }

    @Test
    public void updatingTheSameSpaceWillResultInAnErrorException() {
        board = createBoardWithoutExceptions();
        board.updateBoard(3, "X");

        Assert.assertFalse(board.updateBoard(3, "O"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf3x3Board() {
        board = createBoardWithoutExceptions();
        int[] moves = {0, 1, 2};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnMiddleRowOf3x3Board() {
        board = createBoardWithoutExceptions();
        int[] moves = {3, 4, 5};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnBottomRowOf3x3Board() {
        board = createBoardWithoutExceptions();
        int[] moves = {6, 7, 8};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftColumnOf3x3Board() {
        board = createBoardWithoutExceptions();
        int[] moves = {0, 3, 6};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnMiddleColumnOf3x3Board() {
        board = createBoardWithoutExceptions();
        int[] moves = {1, 4, 7};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnRightColumnOf3x3Board() {
        board = createBoardWithoutExceptions();
        int[] moves = {2, 5, 8};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftDiagonalOf3x3Board() {
        board = createBoardWithoutExceptions();
        int[] moves = {0, 4, 8};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnRightDiagonalOf3x3Board() {
        board = createBoardWithoutExceptions();
        int[] moves = {2, 4, 6};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf5x5Board() {
        board = createBoardWithoutExceptions(5);
        int[] moves = {0, 1, 2, 3, 4};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf100x100Board() {
        int boardSize = 100;
        board = createBoardWithoutExceptions(boardSize);

        updateCellsOnLargeBoard(boardSize, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnTopRowOf1000x1000Board() {
        int boardSize = 1000;
        board = createBoardWithoutExceptions(boardSize);

        updateCellsOnLargeBoard(boardSize, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheGameHasAWinningSetOnLeftDiagonalOf6x6Board() {
        board = createBoardWithoutExceptions(6);
        int[] moves = {0, 7, 14, 21, 28, 35};

        updateCellsOnBoard(moves, "X");

        Assert.assertTrue(board.hasWinningSet("X"));
    }

    @Test
    public void checkIfTheBoardHasNoMoreMoves() {
        board = createBoardWithoutExceptions();
        int[] moves = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        String[] markers = {"X", "O", "X", "O", "O", "X", "O", "X", "X"};

        updateCellsOnBoard(moves, markers);

        Assert.assertTrue(board.hasNoMoreMoves());
    }

    @Test
    public void checkIfTheBoardHasMoves() {
        board = createBoardWithoutExceptions();
        int[] moves = {0, 1, 2, 3, 4};
        String[] markers = {"X", "O", "X", "O", "O"};

        updateCellsOnBoard(moves, markers);

        Assert.assertFalse(board.hasNoMoreMoves());
    }

    private Board createBoardWithoutExceptions(int boardSize) {
        try {
            return new Board(boardSize);
        }

        catch (Exception e) {
            return null;
        }
    }

    private Board createBoardWithoutExceptions() {
        try {
            return new Board();
        }

        catch (Exception e) {
            return null;
        }
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

    private void updateCellsOnLargeBoard(int boardSize, String marker){
        for (int i = 0; i < boardSize; i++) {
            board.updateBoard(i, marker);
        }
    }
}
