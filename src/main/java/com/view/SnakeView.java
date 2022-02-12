package main.java.com.view;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import main.java.com.utility.Position;

public class SnakeView {

    private final List<Rectangle> body;

    public SnakeView(final List<Rectangle> b) {
        body = b;
    }

    public List<Rectangle> getSnakeView() {
        return body;
    }

    public Rectangle getHeadView() {
        return body.get(0);
    }

    public void setBody(final List<Position> list) {
        body.clear();
        list.stream().forEach(p -> {
            body.add(new Rectangle(new Point(p.getX(), p.getY()), new Dimension(32, 32)));
        });
    }
}
