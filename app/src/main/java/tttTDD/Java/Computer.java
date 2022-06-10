package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

import java.util.Scanner;

public class Computer implements Player {
    private final String marker;
    private int previousMove;

    public Computer(String marker) {
        this.marker = marker;
    }

    public Computer(String marker, Scanner sc) {
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
        return previousMove + 1;
    }
}
