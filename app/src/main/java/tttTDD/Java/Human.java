package tttTDD.Java;
import tttTDD.Java.Interfaces.Player;

public class Human implements Player {
    private String marker;

    Human(String marker) {
        this.marker = marker;
    }

    public String getMarker() {
        return this.marker;
    }

    public boolean isComputer() {
        return false;
    }
}
