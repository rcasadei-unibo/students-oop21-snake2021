package main.java.com.controller;

import java.awt.Point;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

import main.java.com.model.Model;
import main.java.com.view.BasicWindow;
import main.java.com.view.GameObserver;
import main.java.com.view.GameOver;
import main.java.com.view.GameView;

public class CollisionManagerImpl implements CollisionManager {

    private final ScoreManager sm;
    private final BasicWindow gameOver;

    public CollisionManagerImpl(final ScoreManager scoreMan, final GameObserver observer) {
        sm = scoreMan;
        gameOver = new GameOver();
        gameOver.setObserver(observer);
    }

    @Override
    public void manageAppleCollision(final GameView view, final Model model) {
        if (detectAppleCollision(view)) {
            model.eatApple();
            sm.updateScore();
            sm.saveScore();
            sm.showHiScore();
        }
    }

    @Override
    public void manageWallOrBodyCollision(final GameView view, final Model model) {
        if (detectWallCollision(view) || detectBodyCollision(view)) {
            model.getSnake().die();
            view.getFrame().setEnabled(false);
            gameOver.show();
        }
    }

    /**
     * 
     * @param view
     * @return true if the Rectangle representing the apple contains the Point2D at the center of snake's head, false otherwise.
     */
    private boolean detectAppleCollision(final GameView view) {
        return view.getMapView().getAppleRect().contains(view.getMapView().getSnakeHeadCenter());
    }

    private boolean detectWallCollision(final GameView view) {
        final Point2D head = view.getMapView().getSnakeHeadCenter();
        // Very ugly. Can't find out why first call of getSnakeHeadCenter() returns a point at (0,0)
        if (head.equals(new Point(0, 0))) {
            return false;
        }
        return !view.getMapView().getMapBounds().contains(head);
    }

    private boolean detectBodyCollision(final GameView view) {
        // TODO Might be better to use a stream
        for (final Rectangle r : view.getMapView().getSnakeBody()) {
            if (r.contains(view.getMapView().getSnakeHeadCenter())) {
                return true;
            }
        }
        return false;
    }

}
