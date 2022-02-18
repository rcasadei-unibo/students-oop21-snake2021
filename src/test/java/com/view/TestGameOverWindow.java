package test.java.com.view;

import main.java.com.controller.Controller;
import main.java.com.view.GameOver;

public class TestGameOverWindow {

    public static void main(final String[] args) {
        final Controller c = new Controller();
        final GameOver go = new GameOver();
        go.setObserver(c);
        go.show();
    }
}
