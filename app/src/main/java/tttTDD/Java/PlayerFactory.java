package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;
import java.util.Scanner;

public class PlayerFactory {
    int selection;
    String marker;
    Scanner sc;

    public Player createPlayer(int selection, String marker, Scanner sc) {
        return switch (selection) {
            case 1 -> new Human(marker, sc);
            case 2 -> new RandomComputer(marker, sc);
            default ->  new Computer(marker, sc);
        };
    }
}
