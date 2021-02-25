package Game;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board extends Pane {

    private final int width = 800;
    private final int height = 600;
    private final int TILE_SIZE = 40;

    private final int X_TILES = width / TILE_SIZE;
    private final int Y_TILES = height / TILE_SIZE;
    private final int BOMBS_NUMBER = (int) (0.15 * X_TILES * Y_TILES);

    Tile[][] tiles = new Tile[X_TILES][Y_TILES];


    public Board() {

        this.setPrefSize(width,height);

        putBombsRandom();
        prepareRestTiles();
        calculateTilesValues();
    }


    private void putBombsRandom() {

        Random random = new Random();

        for(int i = 0; i < BOMBS_NUMBER ; i++) {
            int x = random.nextInt(X_TILES), y = random.nextInt(Y_TILES);

            Tile tile = new Tile(x,y,true);
            tile.setBomb();
            tiles[x][y] = tile;

            this.getChildren().add(tile);
        }
    }

    private void prepareRestTiles() {
        for(int x = 0; x < X_TILES; x++) {
            for(int y = 0; y < Y_TILES; y++) {

                if(tiles[x][y] == null) {
                    tiles[x][y] = new Tile(x, y, false);

                    this.getChildren().add(tiles[x][y]);
                }

            }
        }
    }

    private void calculateTilesValues() {

        for(int x = 0; x < X_TILES; x++) {
            for(int y = 0; y < Y_TILES; y++) {

                if (!tiles[x][y].isHasBomb()) {
                    List<Tile> tileList = getNeighbours(tiles[x][y]);
                    long value = tileList.stream().filter(t -> t.isHasBomb()).count();
                    tiles[x][y].setValue((int)value);
                }

            }
        }

    }

    private List<Tile> getNeighbours(Tile tile) {
        int points[] = new int[]{
                -1,-1,
                0,-1,
                1,-1,
                1,0,
                1,1,
                0,1,
                -1,1,
                -1,0
        };

        List<Tile> tileList = new ArrayList<>();

        for(int i = 0; i < points.length; i++) {
            int newX = points[i] + tile.getX();
            int newY = points[++i] + tile.getY();

            if (newX >= 0 && newX < X_TILES && newY >= 0 && newY < Y_TILES)
                tileList.add(tiles[newX][newY]);
        }

        return tileList;
    }


}
