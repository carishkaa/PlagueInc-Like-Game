/*
 * Authors: Karina Balagazova and Daria Dunina
 * FEL OI PJV 2019
 */
package fel.pjv.client;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

class ModelMap {

    private boolean[][] isClicked;
    private final int CELL_SIZE = 8;

    ModelMap(int rows, int columns) {
        isClicked = new boolean[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                isClicked[i][j] = false;
            }
        }
    }

    void fillCell(int x, int y, Canvas canvas, int red, int green) {
        if (!isClicked[x][y]) {
            System.out.println("Fill cell x=" + x + ", y=" + y + " ");
            isClicked[x][y] = true;
        }

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.rgb(red, green, 0));
        gc.fillRect(
                (x - 0.5) * CELL_SIZE,
                (y - 0.5) * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE
        );
    }
}
