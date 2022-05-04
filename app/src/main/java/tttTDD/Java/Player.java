package tttTDD.Java;

public class Player {
    private String marker;
    private boolean ai;

    Player(String marker, boolean isComputer) {
        this.marker = marker;
        this.ai = isComputer;
    }

    public String getMarker() {
        return this.marker;
    }

    public boolean isComputer() {
        return this.ai;
    }
}
