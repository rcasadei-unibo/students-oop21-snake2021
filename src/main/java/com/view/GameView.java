package main.java.com.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public interface GameView extends BasicWindow {

    /**
     * Updates the view.
     */
    void updateView();

    /**
     * Enables the Pause, Reset and Quit buttons.
     */
    void enableButtons();

    /**
     * 
     * @return the instance of the MapView.
     */
    MapView getMapView();

    /**
     * 
     * @return the main JFrame of the view.
     */
    JFrame getFrame();

    /**
     * 
     * @return the JLabel in which the score is written.
     */
    JLabel getScoreLabel();

    /**
     * 
     * @return the JLabel in which the highscore is written.
     */
    JLabel getHiScoreLabel();

    /**
     * Shows the game over window on screen.
     */
    void showGameOver();

}
