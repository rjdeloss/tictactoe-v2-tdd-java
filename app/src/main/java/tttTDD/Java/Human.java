package tttTDD.Java;
import tttTDD.Java.Interfaces.Player;

import java.util.Scanner;

public class Human implements Player {
    private String marker;
    private Scanner input;

    Human(String marker, Scanner sc) {
        this.marker = marker;
        this.input = sc;
    }

    public String getMarker() {
        return this.marker;
    }

    public boolean isComputer() {
        return false;
    }

    @Override
    public int move(Board board) {
        return this.input.nextInt();
    }
}
