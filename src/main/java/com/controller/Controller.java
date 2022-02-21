package main.java.com.controller;

import java.awt.event.WindowEvent;
import java.util.Queue;

import java.util.concurrent.ArrayBlockingQueue;

import main.java.com.model.GameModel;

import main.java.com.model.Model;
import main.java.com.view.GameView;
import main.java.com.view.GameViewImpl;

/**
 * This class represents the controller's entry point.
 * This class' purpose is to connect together the view and the model of the application and make them interact correctly.
 * Here is implemented the method used to run the application, which is mainLoop().
 *
 */
public class Controller implements GameObserver, InputController {

    private static final long PERIOD = 90;

    private final Model model;
    private final GameView view;
    private final ScoreManager sm;
    private final CollisionManager cm;
    private final Queue<Command> cmdQueue;
    private boolean isPaused;
    private boolean isStarted;
    private boolean quit;

    /**
     * Constructor for the Controller class.
     * Initializes the {@link Model}, the {@link GameView}, the {@link ScoreManager} and the {@link CollisionManager}
     * and also creates command queue.
     * Paints the the view for the first time.
     */
    public Controller() {
        model = new GameModel();
        view = new GameViewImpl(model.getGameMap().getXMapSize(), model.getGameMap().getYMapSize());
        sm = new ScoreManagerImpl(view, model);
        cm = new CollisionManagerImpl(sm);
        view.setObserver(this);
        view.getMapView().addKeyListener(new KeyNotifier(this));
        cmdQueue = new ArrayBlockingQueue<>(100);
        view.getMapView().getAppleView().setPosition(model.getApple().getPosition());
        view.getMapView().getSnakeView().setBody(model.getSnake().getBodyPosition());
        view.show();
        sm.showHiScore();
    }

    /**
     * This method represents the game's main loop.
     * It is called to start the application and keeps looping until the players quits the game.
     */
    public void mainLoop() {
        while (!quit) {
            final long current = System.currentTimeMillis();
            if (!isPaused && isStarted) {
                processInput();
                view.getMapView().getAppleView().setPosition(model.getApple().getPosition());
                view.getMapView().getSnakeView().setBody(model.getSnake().getBodyPosition());
                cm.manageAppleCollision(view, model);
                cm.manageWallOrBodyCollision(view, model);
                model.moveSnake();
                view.updateView();
            }
            waitForNextFrame(current);
        }
    }

    /**
     * Getter for the game's view.
     * @return the {@link GameView} view
     */
    public GameView getView() {
        return view;
    }

    /** {@inheritDoc} */
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

    /** {@inheritDoc} */
    @Override
    public void pauseGame() {
        isPaused = !isPaused;
        if (!isPaused) {
            view.getMapView().requestFocusInWindow();
            view.enableButtons();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void quit() {
        quit = true;
        view.getFrame().dispatchEvent(new WindowEvent(view.getFrame(), WindowEvent.WINDOW_CLOSING));
        //System.exit(0);
    }

    /** {@inheritDoc} */
    public void start() {
        view.enableButtons();
        view.getMapView().setFocusable(true);
        view.getMapView().requestFocusInWindow();
        isStarted = true;
    }

    /** {@inheritDoc} */
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

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < PERIOD) {
            try {
                Thread.sleep(PERIOD - dt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
