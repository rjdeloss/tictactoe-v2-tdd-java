package tttTDD.Java.doubles;

import tttTDD.Java.Board;
import tttTDD.Java.Interfaces.Player;

import java.util.Arrays;

public class TestPlayer implements Player {
    private final String marker;
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
        int currentMove = moves[0];
        moves = Arrays.copyOfRange(moves, 1, moves.length);

        return currentMove;
    }

    @Override
    public int getPreviousMove() {
        return 0;
    }
}
