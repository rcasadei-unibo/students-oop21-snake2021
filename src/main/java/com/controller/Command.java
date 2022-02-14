package main.java.com.controller;

import main.java.com.model.Model;
import main.java.com.utility.Direction;

public interface Command {

    void execute(Model gameModel);

    Direction getDir();
}
