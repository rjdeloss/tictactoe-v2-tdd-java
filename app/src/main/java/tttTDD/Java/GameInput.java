package tttTDD.Java;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GameInput {

    public static int playerSelection(Scanner input, Display display) {
        int value;

        try {
            value = input.nextInt();
            if (value > 0 || value < 4) {
                return value;
            }
        }

        catch (Exception e) {
            System.out.println(display.wrongTypeMessage());
            input.nextLine();
            return  playerSelection(input, display);
        }

        return value;
    }

    public static int boardSize(Scanner input, Display display) {
        try {
            int value = input.nextInt();

            if (value > 2) {
                return value;
            } else {
                throw new Exception();
            }
        }

        catch (InputMismatchException e) {
            System.out.println(display.wrongTypeMessage());
            input.nextLine();
            return  boardSize(input, display);
        }

        catch (Exception e) {
            System.out.println(display.negativeBoardSizeMessage());
            input.nextLine();
            return  boardSize(input, display);
        }
    }
}
