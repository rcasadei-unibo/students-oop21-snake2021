package main.java.com.view;

public interface BasicWindow {

    /**
     * Shows the window on the screen.
     */
    void show();

    /**
     * Sets the {@link GameObserver} for this window.
     * @param obs
     */
    void setObserver(GameObserver obs);
}
