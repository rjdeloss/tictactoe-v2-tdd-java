package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

public class RandomComputer implements Player {
    private final String marker;
    private int previousMove;

    public RandomComputer(String marker) {
        this.marker = marker;
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public boolean isComputer() {
        return true;
    }

    @Override
    public int move(Board board) {
        Integer[] availableMoves = board.getAvailableMoves();
        int location = (int) Math.floor((Math.random()*(availableMoves.length)));
        previousMove = availableMoves[location];

        return previousMove;
    }

    @Override
    public int getPreviousMove() {
        return previousMove + 1;
    }
}
