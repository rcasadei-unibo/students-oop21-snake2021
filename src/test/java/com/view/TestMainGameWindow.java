package test.java.com.view;


import main.java.com.view.View;
import main.java.com.view.GameView;

public class TestMainGameWindow {

    public static void main(final String[] args) {
        final View gv = new GameView(21, 21);
        gv.show();
    }
}
