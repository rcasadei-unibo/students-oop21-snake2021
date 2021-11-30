package main.java.com.model;

import java.util.Optional;
import java.util.Set;

import main.java.com.utility.Direction;
import main.java.com.utility.Position;

public final class Snake implements SnakeEntity {

    /** The direction in which the snake is moving. */
    private Direction direction;
    /** The current position of the snake's head. */
    private Position headPosition;
    /** A set representing all of the snake's body's positions. */
    private Set<Position> body;
    /** The size of the map on the x-coordinate. */
    private final int mapSizeX;
    /** The size of the map on the y-coordinate. */
    private final int mapSizeY;

    private Snake(final Direction dir, final Position headPos, final Set<Position> bodyPos,
                    final int x, final int y) {
        direction = dir;
        headPosition = headPos;
        body = bodyPos;
        mapSizeX = x;
        mapSizeY = y;
    }

    /**
     * Nested builder class used to create a single instance of Snake.
     *
     */
    public static final class SnakeBuilder {
        private static final int MINIMUM_SIZE = 5;
        private Optional<Direction> direction = Optional.empty();
        private Optional<Position> headPosition = Optional.empty();
        private Optional<Set<Position>> body = Optional.empty();
        private Optional<Integer> x = Optional.empty();
        private Optional<Integer> y = Optional.empty();
        private boolean built = false;

        /**
         * 
         * @param dir represents the direction.
         * @return this builder.
         */
        public SnakeBuilder direction(final Direction dir) {
            this.direction = Optional.ofNullable(dir);
            return this;
        }

        /**
         * 
         * @param pos represents the snake's head's position.
         * @return this builder.
         */
        public SnakeBuilder headPosition(final Position pos) {
            this.headPosition = Optional.of(pos);
            return this;
        }

        /**
         * 
         * @param bodyPos a Set containing all the positions of the body.
         * @return this builder.
         */
        public SnakeBuilder body(final Set<Position> bodyPos) {
            this.body = Optional.of(bodyPos);
            return this;
        }

        public SnakeBuilder mapSize(final int x, final int y) {
            if (x < MINIMUM_SIZE || y < MINIMUM_SIZE) { // The game map cannot be smaller than 5x5.
                throw new IllegalArgumentException();
            }
            this.x = Optional.of(x);
            this.y = Optional.of(y);
            return this;
        }
    }

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Direction getDirection() {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void setDirection(final Direction dir) {
        // TODO Auto-generated method stub

    }

    /** {@inheritDoc} */
    @Override
    public Position nextPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Set<Position> getBodyPosition() {
        // TODO Auto-generated method stub
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public void increaseLength() {
        // TODO Auto-generated method stub

    }

    /** {@inheritDoc} */
    @Override
    public void move() {
        // TODO Auto-generated method stub

    }

}
