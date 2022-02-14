package main.java.com.controller;

public interface ScoreManager {

    /**
     * Updates the current score and shows it on the screen.
     */
    void updateScore();

    /**
     * Saves and stores the final score on a file.
     */
    void saveScore();
}
