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
        previousMove = board.getFirstAvailableMove();
        return previousMove;
    }

    @Override
    public int getPreviousMove() {
        return previousMove;
    }
}
