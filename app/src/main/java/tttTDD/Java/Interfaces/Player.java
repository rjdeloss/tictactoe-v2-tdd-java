package tttTDD.Java.Interfaces;

import tttTDD.Java.Board;

public interface Player {
    String getMarker();
    boolean isComputer();
    int move(Board board);
}
