package main.java.com.controller;

import main.java.com.model.GameModel;

import main.java.com.model.Model;
import main.java.com.utility.Direction;
import main.java.com.utility.Pos;
import main.java.com.view.GameObserver;
import main.java.com.view.GameView;
import main.java.com.view.GameViewImpl;

public class Controller implements GameObserver {

    private static final long PERIOD = 500;

    private final Model model;
    private final GameView view;
    private boolean isGameOver;
    private boolean isPaused;

    public Controller() {
        model = new GameModel();
        view = new GameViewImpl();
        view.setObserver(this);
    }

    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        model.getSnake().setDirection(Direction.RIGHT);
        while (true) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);

            view.getMapView().getAppleView().setPosition(model.getApple().getPosition());
            view.getMapView().getSnakeView().setBody(model.getSnake().getBodyPosition());
            model.moveSnake();

            view.updateView();
            waitForNextFrame(current);
            lastTime = current;
        }
    }

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException e) {

            }
        }
    }

    public GameView getView() {
        return view;
    }

    @Override
    public void resetGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pauseGame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void quit() {
        // TODO Auto-generated method stub

    }
}
