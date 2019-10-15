package Utils;

public enum Color {
    RED,
    BLUE,
    GREEN,
    YELLOW,
    WHITE,
    PINK;

    private String getEscape(Color color) {
        switch (color) {
            case WHITE:
                return "\u001b[48;5;0m";
            case RED:
                return "\u001b[48;5;160m";
            case GREEN:
                return "\u001b[48;5;28m";
            case YELLOW:
                return "\u001b[48;5;226m";
            case BLUE:
                return "\u001b[48;5;20m";
            case PINK:
                return "\u001b[48;5;165m";
            default:
                return "\u001b[48;5;0m"; //WHITE
        }
    }

    public String makeColor(Color color, String data) {
        return "\u001b[1m" + getEscape(color) + data + "\u001b[0m";
    }
}
