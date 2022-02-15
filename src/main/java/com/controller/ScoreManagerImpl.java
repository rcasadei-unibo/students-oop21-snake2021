package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.view.GameView;

public class ScoreManagerImpl implements ScoreManager {

    private static final String SCORE = "Score: ";
    private static final String SCORE_0 = "Score: 0";

    private final GameView view;
    private final Model model;

    public ScoreManagerImpl(final GameView gv, final Model gm) {
       view = gv;
       model = gm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore() {
        view.getScoreLabel().setText(SCORE + model.getScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScore() {
        // TODO Auto-generated method stub
    }

}
