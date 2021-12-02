package main.java.com.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import main.java.com.utility.Direction;
import main.java.com.utility.Pos;
import main.java.com.utility.Position;

public class GameMapImpl implements GameMap {

    private static final int INITIAL_BODY_LENGTH = 5;
    /** A set representing all possible positions on the map. */
    private final Set<Position> map;
    /** The map's size on the x-coordinate. */
    private final int xMapSize;
    /** The map's size on the x-coordinate. */
    private final int yMapSize;

    private final Position snakeStartPosition;

    private SnakeEntity snake;
    private EatableEntity apple;

    /**
     * Public constructor for the game map.
     * @param m the set with all the possible positions on the map.
     * @param x map's size on the x-coordinate.
     * @param y map's size on the y-coordinate.
     */
    public GameMapImpl(final Set<Position> m, final int x, final int y) {
        map = m;
        xMapSize = x;
        yMapSize = y;
        snakeStartPosition = new Pos(xMapSize / 2, yMapSize / 2);
        final List<Position> initialBody = new ArrayList<>();
        initialBody.add(snakeStartPosition);
        for (int i = 1; i < INITIAL_BODY_LENGTH; i++) {
            initialBody.add(new Pos(xMapSize / 2, yMapSize / 2 + i));
        }
        snake = new Snake.SnakeBuilder()
                         .direction(Direction.UP)
                         .headPosition(snakeStartPosition)
                         .body(initialBody)
                         .mapSize(xMapSize, yMapSize)
                         .build();
        apple = new Apple(this.randomApplePos());
    }

    /** {@inheritDoc} */
    @Override
    public Set<Position> getAllCells() {
        return this.map;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Position> getFreeCells() {
        final Set<Position> temp = Set.copyOf(this.map);
        if (temp.removeAll(Set.copyOf(this.snake.getBodyPosition()))) {
            return temp;
        } else {
            throw new IllegalStateException();
        }
    }

    /** {@inheritDoc} */
    public Set<Position> getWalls() {
        final Set<Position> walls = new HashSet<>();
        for (int i = 0; i < xMapSize; i++) {
            walls.add(new Pos(i, yMapSize - 1));
            walls.add(new Pos(i, 0));
        }
        for (int j = 0; j < yMapSize; j++) {
            walls.add(new Pos(xMapSize - 1, j));
            walls.add(new Pos(0, j));
        }
        return walls;
    }

    /** {@inheritDoc} */
    @Override
    public void resetMap() {
        // TODO Auto-generated method stub
    }

    /** {@inheritDoc} */
    @Override
    public void updateApple() {
        // TODO Auto-generated method stub

    }

    /** {@inheritDoc} */
    @Override
    public void updateSnake() {
        // TODO Auto-generated method stub
        this.snake.move();
    }

    /** {@inheritDoc} */
    public SnakeEntity getSnake() {
        return this.snake;
    }

    /** {@inheritDoc} */
    public EatableEntity getApple() {
        return this.apple;
    }

    /**
     * 
     * @return a new random position in the game map that doesn't overlap with snake.
     */
    private Position randomApplePos() {
        final Random rand = new Random();
        int x = rand.nextInt(xMapSize);
        int y = rand.nextInt(yMapSize);
        // Randomize position until you get one that does not overlap with snake.
        while (this.snake.getBodyPosition().contains(new Pos(x, y))) {
            x = rand.nextInt(xMapSize);
            y = rand.nextInt(yMapSize);
        }
        return new Pos(x, y);
    }

}
