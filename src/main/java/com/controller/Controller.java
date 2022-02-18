package main.java.com.controller;

import java.util.Queue;

import java.util.concurrent.ArrayBlockingQueue;

import main.java.com.model.GameModel;

import main.java.com.model.Model;
import main.java.com.view.GameObserver;
import main.java.com.view.GameView;
import main.java.com.view.GameViewImpl;

public class Controller implements GameObserver, InputController {

    private static final long PERIOD = 70;

    private final Model model;
    private final GameView view;
    private final ScoreManager sm;
    private final CollisionManager cm;
    private final Queue<Command> cmdQueue;
    private boolean quit;
    private boolean isPaused;

    public Controller() {
        model = new GameModel();
        view = new GameViewImpl(model.getGameMap().getXMapSize(), model.getGameMap().getYMapSize());
        sm = new ScoreManagerImpl(view, model);
        cm = new CollisionManagerImpl(sm, this);
        view.setObserver(this);
        view.getMapView().addKeyListener(new KeyNotifier(this));
        cmdQueue = new ArrayBlockingQueue<>(100);
    }

    public void mainLoop() {
        long lastTime = System.currentTimeMillis();
        view.start();
        sm.showHiScore();
        while (true) {
            final long current = System.currentTimeMillis();
            final int elapsed = (int) (current - lastTime);
            if (!isPaused) {
                processInput();

                view.getMapView().getAppleView().setPosition(model.getApple().getPosition());
                view.getMapView().getSnakeView().setBody(model.getSnake().getBodyPosition());

                cm.manageAppleCollision(view, model);
                cm.manageWallOrBodyCollision(view, model);
                
                model.moveSnake();

                // Check "collision". Probably the check needs to be done graphically.
                /*
                if (model.getSnake().getPosition().equals(model.getApple().getPosition())) {
                    model.eatApple();
                    sm.updateScore();
                    sm.saveScore();
                    sm.showHiScore();
                }
                */

                /*
                if (detectCollision()) {
                    model.eatApple();
                    sm.updateScore();
                    sm.saveScore();
                    sm.showHiScore();
                }
                */


                view.updateView();

            }

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
        model.resetGame();
        sm.updateScore();
        if (isPaused) {
            isPaused = false;
        }
        view.getFrame().setEnabled(true);
        view.getMapView().requestFocusInWindow();
    }

    @Override
    public void pauseGame() {
        isPaused = !isPaused;
        if (!isPaused) {
            view.getMapView().requestFocusInWindow();
        }
    }

    @Override
    public void quit() {
        //quit = true;
        System.exit(0);
    }

    @Override
    public void notifyCommand(final Command cmd) {
        if (!cmd.getDir().equals(model.getSnake().getDirection())) {
            cmdQueue.add(cmd);
        }
    }

    private void processInput() {
        final Command cmd = cmdQueue.poll();
        if (cmd != null) {
            cmd.execute(model);
        }
    }
}
