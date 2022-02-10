package main.java.com.view;

public interface GameObserver {

    /**
     * Resets the state of the game.
     */
    void resetGame();

    /**
     * Pauses the game.
     */
    void pauseGame();

    /**
     * Quits the game closing the application.
     */
    void quit();
}
