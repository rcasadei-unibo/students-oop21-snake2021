package main.java.com.model;

import java.util.HashSet;
import java.util.Set;

import main.java.com.utility.Pos;
import main.java.com.utility.Position;

/**
 * This class represents the game map's model. Implements the {@link GameMap}
 * interface.
 */
public class GameMapImpl implements GameMap {

    private final Set<Position> map;
    private final int xMapSize;
    private final int yMapSize;

    /**
     * Public constructor for the game map.
     * 
     * @param m the set with all the possible positions on the map.
     * @param x map's size on the x-coordinate.
     * @param y map's size on the y-coordinate.
     */
    public GameMapImpl(final Set<Position> m, final int x, final int y) {
        map = m;
        xMapSize = x;
        yMapSize = y;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Position> getAllCells() {
        return this.map;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Position> getFreeCells(final SnakeEntity snake) {
        final Set<Position> temp = new HashSet<>(this.map);
        if (temp.removeAll(Set.copyOf(snake.getBodyPosition()))) {
            return temp;
        } else {
            throw new IllegalStateException();
        }
    }

    /** {@inheritDoc} */
    public Set<Position> getWalls() {
        final Set<Position> walls = new HashSet<>();
        for (int i = 0; i <= xMapSize; i++) {
            walls.add(new Pos(i, yMapSize));
            walls.add(new Pos(i, 0));
        }
        for (int j = 0; j <= yMapSize; j++) {
            walls.add(new Pos(xMapSize, j));
            walls.add(new Pos(0, j));
        }
        return walls;
    }

    /** {@inheritDoc} */
    public int getXMapSize() {
        return this.xMapSize;
    }

    /** {@inheritDoc} */
    public int getYMapSize() {
        return this.yMapSize;
    }

}
