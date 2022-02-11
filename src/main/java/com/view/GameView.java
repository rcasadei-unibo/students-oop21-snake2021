package main.java.com.view;

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
     * @return the instance of the MapView
     */
    MapView getMapView();
}
