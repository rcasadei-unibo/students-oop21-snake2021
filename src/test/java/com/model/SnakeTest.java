package test.java.com.model;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import main.java.com.model.Snake;
import main.java.com.model.SnakeEntity;
import main.java.com.utility.Direction;
import main.java.com.utility.Pos;
import main.java.com.utility.Position;

public class SnakeTest {

    private static final int BODY_LENGTH = 5;
    private static final Position START_POS = new Pos(10, 10);
    private static final int X_MAP_SIZE = 21;
    private static final int Y_MAP_SIZE = 21;

    @Test
    @DisplayName("Can't instantiate snake with missing builder parameters.")
    public void testSnakeBuilderMissingParameters() {
        assertThrows(IllegalStateException.class, () -> {
            @SuppressWarnings("unused")
            final SnakeEntity snake = new Snake.SnakeBuilder().build();
        });
    }

    @Test
    @DisplayName("All fields on the created snake entity should be correct.")
    public void testBuilderFields() {
        final List<Position> body = new ArrayList<>();
        for (int i = 0; i < BODY_LENGTH; i++) {
            body.add(new Pos(START_POS.getX(), START_POS.getY() + i));
        }
        final SnakeEntity snake = new Snake.SnakeBuilder()
                                           .direction(Direction.UP)
                                           .headPosition(START_POS)
                                           .body(body)
                                           .mapSize(X_MAP_SIZE, Y_MAP_SIZE)
                                           .build();
        assertEquals(Direction.UP, snake.getDirection());
        assertEquals(new Pos(10, 10), snake.getPosition());
        for (int j = 0; j < BODY_LENGTH; j++) {
            assertTrue(snake.getBodyPosition().contains(new Pos(START_POS.getX(), START_POS.getY() + j)));
        }
    }

    @Test
    @DisplayName("You should not be able to change the direction to the opposite one.")
    public void testDirection() {
        final SnakeEntity snake = new Snake.SnakeBuilder()
                .direction(Direction.UP)
                .headPosition(START_POS)
                .body(new ArrayList<>())
                .mapSize(X_MAP_SIZE, Y_MAP_SIZE)
                .build();
        assertEquals(Direction.UP, snake.getDirection());
        snake.setDirection(Direction.RIGHT);
        assertEquals(Direction.RIGHT, snake.getDirection());
        snake.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, snake.getDirection());
        snake.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, snake.getDirection());
        snake.setDirection(Direction.RIGHT);
        assertNotEquals(Direction.RIGHT, snake.getDirection());
        assertEquals(Direction.LEFT, snake.getDirection());
        snake.setDirection(Direction.UP);
        snake.setDirection(Direction.DOWN);
        assertNotEquals(Direction.DOWN, snake.getDirection());
        assertEquals(Direction.UP, snake.getDirection());
    }

    @Test
    @DisplayName("The next position should be computed correctly.")
    public void testNextPosition() {
        final SnakeEntity snake = new Snake.SnakeBuilder()
                .direction(Direction.UP)
                .headPosition(START_POS)
                .body(new ArrayList<>())
                .mapSize(X_MAP_SIZE, Y_MAP_SIZE)
                .build();
        assertEquals(new Pos(START_POS.getX(), START_POS.getY() - 1), snake.nextPosition());
        snake.setDirection(Direction.RIGHT);
        assertEquals(new Pos(START_POS.getX() + 1, START_POS.getY()), snake.nextPosition());
        snake.setDirection(Direction.DOWN);
        assertEquals(new Pos(START_POS.getX(), START_POS.getY() + 1), snake.nextPosition());
        snake.setDirection(Direction.LEFT);
        assertEquals(new Pos(START_POS.getX() - 1, START_POS.getY()), snake.nextPosition());
    }

    @Test
    @DisplayName("Verify that the move() method works correctly.")
    public void testMove() {
        final List<Position> body = new ArrayList<>();
        for (int i = 0; i < BODY_LENGTH; i++) {
            body.add(new Pos(START_POS.getX(), START_POS.getY() + i));
        }
        final SnakeEntity snake = new Snake.SnakeBuilder()
                                           .direction(Direction.UP)
                                           .headPosition(START_POS)
                                           .body(body)
                                           .mapSize(X_MAP_SIZE, Y_MAP_SIZE)
                                           .build();
        snake.move();
        assertEquals(new Pos(START_POS.getX(), START_POS.getY() - 1), snake.getPosition());
        assertFalse(snake.getBodyPosition().contains(new Pos(START_POS.getX(), START_POS.getY() + BODY_LENGTH - 1)));
        snake.move();
        assertEquals(new Pos(START_POS.getX(), START_POS.getY() - 2), snake.getPosition());
        assertFalse(snake.getBodyPosition().contains(new Pos(START_POS.getX(), START_POS.getY() + BODY_LENGTH - 2)));
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        assertEquals(new Pos(START_POS.getX(), 0), snake.getPosition());
        snake.move(); // Here we try to move past the coordinate 0, the position should not change.
        assertEquals(new Pos(START_POS.getX(), 0), snake.getPosition());
        snake.setDirection(Direction.RIGHT);
        snake.move();
        assertEquals(new Pos(START_POS.getX() + 1, 0), snake.getPosition());
        assertFalse(snake.getBodyPosition().contains(new Pos(START_POS.getX(), 4)));
        snake.move();
        assertEquals(new Pos(START_POS.getX() + 2, 0), snake.getPosition());
        assertFalse(snake.getBodyPosition().contains(new Pos(START_POS.getX(), 3)));
        // Now we try to simulate as if snake eats an apple.
        snake.increaseLength();
        snake.move();
        assertEquals(new Pos(START_POS.getX() + 3, 0), snake.getPosition());
        assertTrue(snake.getBodyPosition().contains(new Pos(START_POS.getX(), 2))); // The tail should not be removed now because the length was increased.
        snake.move();
        assertEquals(new Pos(START_POS.getX() + 4, 0), snake.getPosition());
        assertFalse(snake.getBodyPosition().contains(new Pos(START_POS.getX(), 2)));
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        snake.move();
        assertEquals(new Pos(X_MAP_SIZE, 0), snake.getPosition());
        snake.move(); // Here we try to move past the map's size on the x coordinate, position should not change.
        assertEquals(new Pos(X_MAP_SIZE, 0), snake.getPosition());
        snake.setDirection(Direction.DOWN);
        snake.move();
        assertEquals(new Pos(X_MAP_SIZE, 1), snake.getPosition());
    }
}
