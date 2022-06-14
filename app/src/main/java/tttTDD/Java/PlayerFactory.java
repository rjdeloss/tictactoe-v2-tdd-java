package tttTDD.Java;

import tttTDD.Java.Interfaces.Player;
import java.util.Scanner;

public class PlayerFactory {

    public Player createPlayer(int selection, String marker, Scanner sc) {
        return switch (selection) {
            case 1 -> new Human(marker, sc);
            case 2 -> new Computer(marker);
            default ->  new RandomComputer(marker);
        };
    }
}
