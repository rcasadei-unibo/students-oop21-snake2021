package main.java.com.model;

import java.util.List;
import java.util.Optional;

import main.java.com.utility.Direction;
import main.java.com.utility.Pos;
import main.java.com.utility.Position;

public final class Snake implements SnakeEntity {

    /** The direction in which the snake is moving. */
    private Direction direction;
    /** The current position of the snake's head. */
    private Position headPosition;
    /** A set representing all of the snake's body's positions. */
    private List<Position> body;
    /** The length of the snake. */
    private int length;
    /** The size of the map on the x-coordinate. */
    private final int mapSizeX;
    /** The size of the map on the y-coordinate. */
    private final int mapSizeY;

    /**
     * 
     * @param dir the direction.
     * @param headPos the head's position.
     * @param bodyPos the body's position.
     * @param x the map's size on the x-coordinate.
     * @param y the map's size on the y-coordinate.
     */
    private Snake(final Direction dir, final Position headPos, final List<Position> bodyPos,
                    final int x, final int y) {
        direction = dir;
        headPosition = headPos;
        body = bodyPos;
        length = body.size();
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
        private Optional<List<Position>> body = Optional.empty();
        private Optional<Integer> x = Optional.empty();
        private Optional<Integer> y = Optional.empty();
        private boolean built;

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
        public SnakeBuilder body(final List<Position> bodyPos) {
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

        /**
         * 
         * @return a new instance of Snake with all the parameters correctly initialized.
         */
        public Snake build() {
            if (this.built) {
                throw new IllegalStateException();
            }
            if (this.direction.isEmpty() || this.headPosition.isEmpty() || this.body.isEmpty()
                    || this.x.isEmpty() || this.y.isEmpty()) {
                throw new IllegalStateException();
            }
            this.built = true;
            return new Snake(this.direction.get(), this.headPosition.get(), this.body.get(), this.x.get(), this.y.get());
        }
    }

    /** {@inheritDoc} */
    @Override
    public Position getPosition() {
        return this.headPosition;
    }

    /** {@inheritDoc} */
    public void setPosition(final Position p) {
        if (p.getX() < 0 || p.getX() > this.mapSizeX
                || p.getY() < 0 || p.getY() > this.mapSizeY) {
            throw new IllegalArgumentException();
        }
        this.headPosition = p;
    }

    /** {@inheritDoc} */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /** {@inheritDoc} */
    @Override
    public void setDirection(final Direction dir) {
        if (!Direction.isOppositeDirection(this.direction, dir)) { // Change direction only if the new one is not opposite to the old one.
            this.direction = dir;
        }
    }

    /** {@inheritDoc} */
    @Override
    public Position nextPosition() {
        int x = this.getPosition().getX();
        int y = this.getPosition().getY();
        switch (this.direction) {
        case UP:
            y--;
            break;
        case DOWN:
            y++;
            break;
        case RIGHT:
            x++;
            break;
        case LEFT:
            x--;
            break;
        default:
            break;
        }
        return new Pos(x, y);
    }

    /** {@inheritDoc} */
    @Override
    public List<Position> getBodyPosition() {
        return this.body;
    }

    /** {@inheritDoc} */
    public void setBodyPosition(final List<Position> b) {
        this.body = b;
        this.headPosition = b.get(0);
    }

    /** {@inheritDoc} */
    @Override
    public void increaseLength() {
        // TODO Auto-generated method stub
        this.length++;
    }

    /** {@inheritDoc} */
    @Override
    public void move() {
        // TODO Auto-generated method stub
        // TODO JUnit test.
        // Check if the next position would hit a wall or another body part.
        if (this.body.contains(this.nextPosition())
                || this.nextPosition().getX() < 0 || this.nextPosition().getX() >= this.mapSizeX
                || this.nextPosition().getY() < 0 || this.nextPosition().getY() >= this.mapSizeY) {
            //this.dead = true;
            return;
        }

        /** Probably it has to be done like this. Add the new element on the head and remove the one on the tail.
         */
        this.headPosition = this.nextPosition();
        if (this.length == this.body.size()) {   // The length field should be increased when snake eats an apple,
            this.body.remove(this.body.size() - 1);  // so we could use it to know when not to remove the element on the tail.
        }
        this.body.add(0, this.headPosition);

    }

}
