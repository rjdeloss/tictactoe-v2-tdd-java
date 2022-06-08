package tttTDD.Java.doubles;

import tttTDD.Java.Board;
import tttTDD.Java.Interfaces.Player;

public class TestPlayer implements Player {
    private final String marker;
    private int previousMove;
    private int[] moves;

    public TestPlayer(String marker, int[] moves) {
        this.marker = marker;
        this.moves = moves;
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public boolean isComputer() {
        return false;
    }

    @Override
    public int move(Board board) {
        return moves[0];
    }

    @Override
    public int getPreviousMove() {
        return 0;
    }
}
