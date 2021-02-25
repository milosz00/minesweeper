package Game;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Tile extends StackPane {
    private final int x,y;
    private final boolean hasBomb;
    private boolean open;
    private int value;
    private final int TILE_SIZE = 40;

    Rectangle border = new Rectangle(TILE_SIZE - 2, TILE_SIZE - 2);
    Text text = new Text();

    public Tile(int x, int y, boolean hasBomb) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;


        border.setStroke(Color.LIGHTGRAY);
        text.setText(hasBomb ? "X" : "");
        text.setFont(Font.font(18));
        text.setFill(Color.WHITE);
        text.setVisible(true);

        getChildren().addAll(border, text);

        setTranslateX(x * TILE_SIZE);
        setTranslateY(y * TILE_SIZE);

        setOnMouseClicked(e -> openTile());

    }

    public void openTile() {
        System.out.println(x+ " " + y);
        if(open)
            return;

        if(hasBomb) {
            System.out.println("GAME OVER");
            return;
        }

        open = true;
        text.setVisible(true);


    }

    public void setValue(int value) {
        this.value = value;
        this.text.setText(String.valueOf(value));
    }

    public void setBomb() {
        this.text.setText("X");
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
