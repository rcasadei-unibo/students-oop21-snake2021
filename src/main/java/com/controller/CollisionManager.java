package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.view.GameView;

public interface CollisionManager {

    void manageAppleCollision(GameView view, Model model);

    void manageWallOrBodyCollision(GameView view, Model model);
}
