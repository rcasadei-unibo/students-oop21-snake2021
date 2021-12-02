package main.java.com.model;

public class GameModel implements Model {

    private SnakeEntity snake;
    private EatableEntity apple;
    private GameMap gameMap;
    private int score;

    public GameModel(final SnakeEntity s, final EatableEntity a, final GameMap m) {
        snake = s;
        apple = a;
        gameMap = m;
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
        // TODO Auto-generated method stub
        
    }

    /** {@inheritDoc} */
    @Override
    public void eatApple() {
        // TODO Auto-generated method stub
        
    }

    /** {@inheritDoc} */
    @Override
    public boolean hitWallorBody() {
        // TODO Auto-generated method stub
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void resetGame() {
        // TODO Auto-generated method stub
        
    }
}
