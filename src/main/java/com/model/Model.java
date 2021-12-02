package main.java.com.model;

public interface Model {

    /**
     * 
     * @return snake.
     */
    SnakeEntity getSnake();

    /**
     * 
     * @return the apple.
     */
    EatableEntity getApple();

    /**
     * 
     * @return the game map.
     */
    GameMap getGameMap();

    /**
     * 
     * @return the current score.
     */
    int getScore();

    /**
     * Increases the score by the given value.
     * @param value
     */
    void incScore(int value);

    /**
     * Moves the snake by one step.
     */
    void moveSnake();

    /**
     * To be called when snake's head is on the same position as an apple.
     * Increases the score, increases snake's length by one unit, updates the apple's position
     * to a new random position.
     */
    void eatApple();

    /**
     * 
     * @return true if snake's head hits a wall or another part of its body, false otherwise.
     */
    boolean hitWallorBody();

    /**
     * Reset the game to its original state.
     */
    void resetGame();
}
