package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.view.GameView;

public class CollisionManagerImpl implements CollisionManager {

    private final ScoreManager sm;

    public CollisionManagerImpl(final ScoreManager scoreMan) {
        sm = scoreMan;
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
        if (detectWallOrBodyCollision(view)) {

        }
    }

    private boolean detectAppleCollision(final GameView view) {
        return view.getMapView().getAppleRect().contains(view.getMapView().getSnakeHeadCenter());
    }

    private boolean detectWallOrBodyCollision(final GameView view) {
        return false;
    }

}
