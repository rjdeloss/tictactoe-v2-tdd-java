package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;

public class Computer implements Player {
    private final String marker;

    public Computer(String marker) {
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
        return board.getFirstAvailableMove();
    }
}
