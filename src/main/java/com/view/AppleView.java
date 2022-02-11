package main.java.com.view;

import java.awt.Point;
import java.awt.Rectangle;

import main.java.com.utility.Pos;
import main.java.com.utility.Position;

/**
 * This class represents an Apple graphically as a Rectangle.
 * 
 *
 */
public class AppleView {

    private final Rectangle apple;

    public AppleView(final Rectangle a) {
        apple = a;
    }

    public Rectangle getAppleView() {
        return apple;
    }

    public Position getLocation() {
        return new Pos((int) apple.getX(), (int) apple.getY());
    }

    public void setPosition(final Position p) {
        apple.setLocation(new Point(p.getX(), p.getY()));
    }
}
