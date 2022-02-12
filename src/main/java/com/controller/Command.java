package main.java.com.controller;

import main.java.com.model.Model;

public interface Command {

    void execute(Model gameModel);
}
