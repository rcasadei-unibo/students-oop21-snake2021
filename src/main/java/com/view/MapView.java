package main.java.com.view;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        populateCells();
        apple = new AppleView(new Rectangle(CELL_SIZE, CELL_SIZE));
        snake = new SnakeView(new ArrayList<>());
    }

    /**
     * @param g
     */
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        populateCells();
        //drawGrid(g);
        drawBounds(g);
        drawApple(g);
        drawSnake(g);
    }

    /**
     * Draws the grid of the map.
     * @param g
     */
    private void drawGrid(final Graphics g) {
        final Position start = new Pos((this.getWidth() - xMapSize * CELL_SIZE) / 2,
                                        (this.getHeight() - ymapSize * CELL_SIZE) / 2);
        g.setColor(Color.GREEN);
        for (int i = 0; i <= xMapSize; i++) {
            g.drawLine(start.getX() + i * CELL_SIZE, start.getY() + 0, start.getX() + i * CELL_SIZE, start.getY() + ymapSize * CELL_SIZE);
            if (i <= xMapSize) {
                g.drawLine(start.getX() + 0, start.getY() + i * CELL_SIZE, start.getX() + xMapSize * CELL_SIZE, start.getY() + i * CELL_SIZE);
            }
        }
    }

    /**
     * Draws the bounds of the map.
     * @param g
     */
    private void drawBounds(final Graphics g) {
        final Point2D topLeft = new Point((this.getWidth() - xMapSize * CELL_SIZE) / 2,
                                            (this.getHeight() - ymapSize * CELL_SIZE) / 2);
        final Point2D topRight = new Point((int) (topLeft.getX() + xMapSize * CELL_SIZE), (int) topLeft.getY());
        final Point2D bottomLeft = new Point((int) topLeft.getX(), (int) (topLeft.getY() + ymapSize * CELL_SIZE));
        final Point2D bottomRight = new Point((int) (topLeft.getX() + xMapSize * CELL_SIZE), (int) (topLeft.getY() + ymapSize * CELL_SIZE));
        final Set<Line2D> bounds = new HashSet<>();
        bounds.add(new Line2D.Double(topLeft, topRight));
        bounds.add(new Line2D.Double(topLeft, bottomLeft));
        bounds.add(new Line2D.Double(topRight, bottomRight));
        bounds.add(new Line2D.Double(bottomLeft, bottomRight));
        g.setColor(Color.GREEN);
        bounds.stream().forEach(line -> {
            g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
        });
    }

    /**
     * Draws an apple on the screen.
     * @param g
     */
    private void drawApple(final Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(cells.get(apple.getLocation()).getX(), cells.get(apple.getLocation()).getY(), CELL_SIZE + 1, CELL_SIZE + 1);
    }

    /**
     * Draws snake on the screen.
     * @param g
     */
    private void drawSnake(final Graphics g) {
        g.setColor(Color.WHITE);
        snake.getSnakeView().stream().forEach(r -> {
            final Position p = new Pos((int) r.getLocation().getX(), (int) r.getLocation().getY());
            g.fillRect(cells.get(p).getX(), cells.get(p).getY(), CELL_SIZE + 1, CELL_SIZE + 1);
        });
    }

    /**
     * Populates the map containing all the coordinates for the cells.
     */
    private void populateCells() {
        final Position start = new Pos((this.getWidth() - xMapSize * CELL_SIZE) / 2,
                                        (this.getHeight() - ymapSize * CELL_SIZE) / 2);
        for (int i = -1; i <= xMapSize + 1; i++) {
            for (int j = -1; j <= ymapSize + 1; j++) {
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

    public Rectangle getAppleRect() {
        return new Rectangle(cells.get(apple.getLocation()).getX(), cells.get(apple.getLocation()).getY(), CELL_SIZE + 1, CELL_SIZE + 1);
    }

    public Point2D getSnakeHeadCenter() {
        final Rectangle head = getSnakeHead();
        return new Point((int) (head.getLocation().getX() + head.getWidth() / 2), (int) (head.getLocation().getY() + head.getHeight() / 2));
    }

    public Rectangle getSnakeHead() {
        final Position p = new Pos((int) snake.getSnakeView().get(0).getX(), (int) snake.getSnakeView().get(0).getY());
        return new Rectangle(cells.get(p).getX(), cells.get(p).getY(), CELL_SIZE + 1, CELL_SIZE + 1);
    }

    /**
     * 
     * @return a List of Rectangles representing the graphical body of the snake without the head
     */
    public List<Rectangle> getSnakeBody() {
        /*
        return snake.getSnakeView().stream()
                        .map(r -> new Rectangle(cells.get(new Pos((int) r.getLocation().getX(), (int) r.getLocation().getY())).getX(),
                                                cells.get(new Pos((int) r.getLocation().getX(), (int) r.getLocation().getY())).getY(),
                                                (int) r.getWidth() + 1, (int) r.getHeight() + 1))
                        .collect(Collectors.toList());
        */
        final List<Rectangle> body = new ArrayList<>();
        final List<Rectangle> bodyView = new ArrayList<>(snake.getSnakeView());
        bodyView.remove(0);
        for (final Rectangle r : bodyView) {
            final Position p = cells.get(new Pos((int) r.getX(), (int) r.getY()));
            body.add(new Rectangle(p.getX(), p.getY(), (int) r.getWidth() + 1, (int) r.getHeight() + 1));
        }
        return body;
    }


    public /*Set<Line2D>*/ Rectangle2D getMapBounds() {
        final Position start = new Pos((this.getWidth() - xMapSize * CELL_SIZE) / 2,
                                         (this.getHeight() - ymapSize * CELL_SIZE) / 2);
        return new Rectangle(start.getX(), start.getY(), xMapSize * CELL_SIZE, ymapSize * CELL_SIZE).getBounds2D();
    }

}
