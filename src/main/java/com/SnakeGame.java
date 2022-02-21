package main.java.com;

import main.java.com.controller.Controller;

/**
 * This is the game's entry point.
 *
 */
public class SnakeGame {

    /**
     * The main method that starts the game.
     * @param args no expected arguments.
     */
    public static void main(final String[] args) {
        new Controller().mainLoop();
    }
}
