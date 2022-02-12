package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.utility.Direction;

public class MoveUp implements Command {

    @Override
    public void execute(final Model gameModel) {
        gameModel.getSnake().setDirection(Direction.UP);
    }

}
