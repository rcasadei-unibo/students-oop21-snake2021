package main.java.com.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;


import main.java.com.utility.Pos;
import main.java.com.utility.Position;


public class MapView extends JPanel {

    private static final long serialVersionUID = 7273434234021291999L;
    private static final int CELL_SIZE = 32; // Hard coded, should be changed to be responsive to the window size.

    private final int xMapSize;
    private final int ymapSize;
    private final Map<Position, Position> cells = new HashMap<>();
    private final AppleView apple;
    private final SnakeView snake;

    public MapView(final int x, final int y) {
        xMapSize = x;
        ymapSize = y;
        apple = new AppleView(new Rectangle(CELL_SIZE, CELL_SIZE));
        snake = new SnakeView(new ArrayList<>());
    }

    /**
     * @param g
     */
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        populateCells();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        drawGrid(g);
        drawApple(g);
        drawSnake(g);

        g.setColor(Color.RED);
        g.fillRect(cells.get(new Pos(0, 0)).getX(), cells.get(new Pos(0, 0)).getY(), CELL_SIZE + 1, CELL_SIZE + 1);
        g.clearRect(cells.get(new Pos(0, 0)).getX(), cells.get(new Pos(0, 0)).getY(), CELL_SIZE + 1, CELL_SIZE + 1);

        /*
        g.setColor(Color.GREEN);
        g.fillRect(cells.get(new Pos(20, 20)).getX(), cells.get(new Pos(20, 20)).getY(), CELL_SIZE, CELL_SIZE);
        final Rectangle r = new Rectangle(new Dimension(CELL_SIZE, CELL_SIZE));
        r.setLocation(cells.get(new Pos(4, 5)).getX(), cells.get(new Pos(4, 5)).getY());
        g.setColor(Color.WHITE);
        g.fillRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());

        apple.setPosition(new Pos(10, 10));
        drawApple(g);
        apple.setPosition(new Pos(10, 9));
        drawApple(g);
        apple.setPosition(new Pos(18, 3));
        drawApple(g);
        */
    }

    private void drawGrid(final Graphics g) {
        final Position start = new Pos((this.getWidth() - xMapSize * CELL_SIZE) / 2,
                                        (this.getHeight() - ymapSize * CELL_SIZE) / 2);
        g.setColor(new Color(0, 255, 0));
        for (int i = 0; i <= xMapSize; i++) {
            g.drawLine(start.getX() + i * CELL_SIZE, start.getY() + 0, start.getX() + i * CELL_SIZE, start.getY() + ymapSize * CELL_SIZE);
            if (i <= xMapSize) {
                g.drawLine(start.getX() + 0, start.getY() + i * CELL_SIZE, start.getX() + xMapSize * CELL_SIZE, start.getY() + i * CELL_SIZE);
            }
        }
    }

    private void drawApple(final Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(cells.get(apple.getLocation()).getX(), cells.get(apple.getLocation()).getY(), CELL_SIZE + 1, CELL_SIZE + 1);
    }

    private void drawSnake(final Graphics g) {
        g.setColor(Color.WHITE);
        snake.getSnakeView().stream().forEach(r -> {
            final Position p = new Pos((int) r.getLocation().getX(), (int) r.getLocation().getY());
            g.fillRect(cells.get(p).getX(), cells.get(p).getY(), CELL_SIZE + 1, CELL_SIZE + 1);
        });
    }

    private void populateCells() {
        final Position start = new Pos((this.getWidth() - xMapSize * CELL_SIZE) / 2,
                                        (this.getHeight() - ymapSize * CELL_SIZE) / 2);
        for (int i = 0; i <= xMapSize; i++) {
            for (int j = 0; j <= ymapSize; j++) {
                cells.put(new Pos(i, j), new Pos(start.getX() + i * CELL_SIZE, start.getY() + j * CELL_SIZE));
            }
        }
    }

    public AppleView getAppleView() {
        return apple;
    }

    public SnakeView getSnakeView() {
        return snake;
    }

}
