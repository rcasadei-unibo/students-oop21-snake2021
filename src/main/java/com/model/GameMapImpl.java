package main.java.com.model;

import java.util.ArrayList;
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
        List<Position> initialBody = new ArrayList<>();
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
        Set<Position> temp = Set.copyOf(this.map);
        if (temp.removeAll(Set.copyOf(this.snake.getBodyPosition()))) {
            return temp;
        } else {
            throw new IllegalStateException();
        }
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

    private Position randomApplePos() {
        final Random rand = new Random();
        return new Pos(rand.nextInt(xMapSize), rand.nextInt(yMapSize));
    }

}
