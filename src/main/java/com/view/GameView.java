package main.java.com.view;

import javax.swing.JFrame;
import javax.swing.JLabel;

public interface GameView {

    /**
     * Sets the observer for the view.
     * 
     * @param observer
     */
    void setObserver(GameObserver observer);

    /**
     * Sets the view as visible.
     */
    void start();

    /**
     * Updates the view.
     */
    void updateView();

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

}
