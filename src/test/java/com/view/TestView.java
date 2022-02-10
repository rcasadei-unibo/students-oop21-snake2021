package test.java.com.view;

import main.java.com.view.GameView;
import main.java.com.view.GameViewImpl;

public class TestView {

    public static void main(final String[] args) {
        final GameView gv = new GameViewImpl();
        gv.start();
    }
}
