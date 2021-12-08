package main.java.com.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import main.java.com.utility.Direction;
import main.java.com.utility.Pos;
import main.java.com.utility.Position;

public class GameModel implements Model {

    private static final Direction START_DIR = Direction.UP;
    private static final int INITIAL_BODY_LENGTH = 5;
    private static final int X_MAP_SIZE = 21;
    private static final int Y_MAP_SIZE = 21;
    private static final Position SNAKE_START_POSITION = new Pos(X_MAP_SIZE / 2, Y_MAP_SIZE / 2);

    private final SnakeEntity snake;
    private final EatableEntity apple;
    private final GameMap gameMap;
    private int score;

    public GameModel() {
        snake = new Snake.SnakeBuilder()
                         .direction(START_DIR)
                         .headPosition(SNAKE_START_POSITION)
                         .body(this.getInitialSnake())
                         .mapSize(X_MAP_SIZE, Y_MAP_SIZE)
                         .build();
        final Set<Position> s = new HashSet<>();
        for (int i = 0; i <= X_MAP_SIZE; i++) {
            for (int j = 0; j <= Y_MAP_SIZE; j++) {
                s.add(new Pos(i, j));
            }
        }
        gameMap = new GameMapImpl(s, X_MAP_SIZE, Y_MAP_SIZE);
        apple = new Apple(this.randomApplePos());
        score = 0;
    }

    /** {@inheritDoc} */
    public SnakeEntity getSnake() {
        return this.snake;
    }

    /** {@inheritDoc} */
    public EatableEntity getApple() {
        return this.apple;
    }

    /** {@inheritDoc} */
    public GameMap getGameMap() {
        return this.gameMap;
    }

    /** {@inheritDoc} */
    public int getScore() {
        return this.score;
    }

    /** {@inheritDoc} */
    @Override
    public void incScore(final int value) {
        this.score += value;
    }

    /** {@inheritDoc} */
    @Override
    public void moveSnake() {
        this.snake.move();
    }

    /** {@inheritDoc} */
    @Override
    public void eatApple() {
        if (this.snake.getPosition() != this.apple.getPosition()) {
            throw new IllegalStateException();
        }
        this.incScore(this.apple.getPointsValue());
        this.snake.increaseLength();
        this.apple.incrementEatenCounter();
        this.apple.setPosition(this.randomApplePos());
    }

    /** {@inheritDoc} */
    /*
     * Consider changing this method. If I use snake.getPosition() I have to find a way to check hits with its body.
     */
    @Override
    public boolean hitWallorBody() {
        return this.snake.getBodyPosition().contains(this.snake.nextPosition())
                || this.gameMap.getWalls().contains(this.snake.nextPosition());
    }

    /** {@inheritDoc} */
    @Override
    public void resetGame() {
        this.snake.setBodyPosition(this.getInitialSnake());
        this.apple.setPosition(this.randomApplePos());
        this.apple.resetTimesEaten();
        this.score = 0;
    }

    /**
     * 
     * @return a new random position in the game map that doesn't overlap with snake.
     */
    private Position randomApplePos() {
        final Random rand = new Random();
        int x = rand.nextInt(this.gameMap.getXMapSize());
        int y = rand.nextInt(this.gameMap.getYMapSize());
        // Randomize position until you get one that does not overlap with snake or walls.
        while (this.snake.getBodyPosition().contains(new Pos(x, y)) || this.gameMap.getWalls().contains(new Pos(x, y))) {
            x = rand.nextInt(this.gameMap.getXMapSize());
            y = rand.nextInt(this.gameMap.getYMapSize());
        }
        return new Pos(x, y);
    }

    /**
     * 
     * @return a list representing the initial snake.
     */
    private List<Position> getInitialSnake() {
        final List<Position> initBody = new ArrayList<>();
        for (int i = 0; i < INITIAL_BODY_LENGTH; i++) {
            initBody.add(new Pos(SNAKE_START_POSITION.getX(), SNAKE_START_POSITION.getY() + i));
        }
        return initBody;
    }
}
