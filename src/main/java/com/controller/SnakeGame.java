package main.java.com.controller;

import main.java.com.view.BasicWindow;
import main.java.com.view.GameStart;

public class SnakeGame {

    public static void main(final String[] args) {
        final Controller c = new Controller();
        final BasicWindow gs = new GameStart();
        gs.setObserver(c);
        gs.show();
        c.mainLoop();
    }
}
