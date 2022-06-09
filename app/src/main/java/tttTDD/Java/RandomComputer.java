package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

import java.util.Scanner;

public class RandomComputer implements Player {
    private final String marker;
    private int previousMove;

    public RandomComputer(String marker) {
        this.marker = marker;
    }

    public RandomComputer(String marker, Scanner sc) {
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
        String[] availableMoves = board.getAvailableMoves();
        int location = (int) Math.floor((Math.random()*(availableMoves.length)));
        previousMove = convertToLocation(availableMoves[location]);

        return previousMove;
    }

    @Override
    public int getPreviousMove() {
        return previousMove;
    }

    private int convertToLocation(String space) {
        return Integer.parseInt(space) - 1;
    }
}
