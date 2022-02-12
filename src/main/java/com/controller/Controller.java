package main.java.com.controller;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import main.java.com.model.GameModel;

import main.java.com.model.Model;
import main.java.com.utility.Direction;
import main.java.com.view.GameObserver;
import main.java.com.view.GameView;
import main.java.com.view.GameViewImpl;

public class Controller implements GameObserver, InputController {

    private static final long PERIOD = 100;

    private final Model model;
    private final GameView view;
    private final Queue<Command> cmdQueue;
    private boolean isGameOver;
    private boolean isPaused;

    public Controller() {
        model = new GameModel();
        view = new GameViewImpl();
        view.setObserver(this);
        view.getMapView().addKeyListener(new KeyNotifier(this));
        cmdQueue = new ArrayBlockingQueue<>(100);
    }

    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        model.getSnake().setDirection(Direction.RIGHT);
        while (!isGameOver || !isPaused) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            view.getMapView().requestFocusInWindow();
            processInput();

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

    }

    @Override
    public void pauseGame() {

    }

    @Override
    public void quit() {

    }

    @Override
    public void notifyCommand(final Command cmd) {
        this.cmdQueue.add(cmd);
    }

    private void processInput() {
        final Command cmd = cmdQueue.poll();
        if (cmd != null) {
            cmd.execute(model);
        }
    }
}
