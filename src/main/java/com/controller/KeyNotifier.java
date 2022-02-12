package main.java.com.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyNotifier implements KeyListener {

    private final InputController c;

    public KeyNotifier(final InputController contr) {
        c = contr;
    }

    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            c.notifyCommand(new MoveUp());
            break;
        case KeyEvent.VK_W:
            c.notifyCommand(new MoveUp());
            break;
        case KeyEvent.VK_RIGHT:
            c.notifyCommand(new MoveRight());
            break;
        case KeyEvent.VK_D:
            c.notifyCommand(new MoveRight());
            break;
        case KeyEvent.VK_DOWN:
            c.notifyCommand(new MoveDown());
            break;
        case KeyEvent.VK_S:
            c.notifyCommand(new MoveDown());
            break;
        case KeyEvent.VK_LEFT:
            c.notifyCommand(new MoveLeft());
            break;
        case KeyEvent.VK_A:
            c.notifyCommand(new MoveLeft());
            break;
        default:
            break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }

}
