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
        if (detectWallCollision(view) || detectBodyCollision(view)) {
            model.getSnake().die();
            view.getFrame().setEnabled(false);
            view.showGameOver();
        }
    }

    private boolean detectAppleCollision(final GameView view) {
        return view.getMapView().getAppleRect().contains(view.getMapView().getSnakeHeadCenter());
    }

    private boolean detectWallCollision(final GameView view) {
        return !view.getMapView().getMapBounds().contains(view.getMapView().getSnakeHeadCenter());
    }

    private boolean detectBodyCollision(final GameView view) {
        return view.getMapView().getSnakeBody().stream().anyMatch(r -> r.contains(view.getMapView().getSnakeHeadCenter()));
    }

}
