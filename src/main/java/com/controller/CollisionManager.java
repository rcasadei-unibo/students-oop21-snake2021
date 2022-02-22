package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.view.View;

public interface CollisionManager {

    void manageAppleCollision(View view, Model model);

    void manageWallOrBodyCollision(View view, Model model);
}
