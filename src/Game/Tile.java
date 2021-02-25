package Game;

import javafx.scene.layout.StackPane;

public class Tile extends StackPane {
    private final int x,y;
    private final boolean hasBomb;
    private int value;

    public Tile(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHasBomb() {
        return hasBomb;
    }
}
