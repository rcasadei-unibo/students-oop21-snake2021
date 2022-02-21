package main.java.com.controller;

public interface InputController {

    /**
     * Notifies the given {@link Command}.
     * @param cmd the {@link Command} to be notified
     */
    void notifyCommand(Command cmd);
}
