package tttTDD.Java;
import tttTDD.Java.Interfaces.Player;

import java.util.Scanner;

public class Human implements Player {
    private final String marker;
    private Scanner playerInput;
    private int previousMove;

    Human(String marker) {
        this.marker = marker;
    }

    Human(String marker, Scanner sc) {
        this.marker = marker;
        this.playerInput = sc;
    }

    public String getMarker() {
        return this.marker;
    }

    public int getPreviousMove() {
        return this.previousMove;
    }

    public boolean isComputer() {
        return false;
    }

    @Override
    public int move(Board board) {
        try {
            int move = playerInput.nextInt();
            previousMove = move;

            return convertInputToLocation(move);
        }

        catch (Exception e) {
            System.out.println("Stop it. Enter a number as an input:");
            playerInput.nextLine();
            return  move(board);
        }
    }

    private int convertInputToLocation(int value) {
        return value - 1;
    }
}
