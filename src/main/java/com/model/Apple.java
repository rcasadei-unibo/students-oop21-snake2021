package main.java.com.model;

import main.java.com.utility.Position;

public class Apple implements EatableEntity {

    private Position position;
    private int eatenCounter;

    public Apple(final Position pos) {
        position = pos;
        eatenCounter = 0;
    }

    /** {@inheritDoc}*/
    @Override
    public Position getPosition() {
        return this.position;
    }

    /** {@inheritDoc}*/
    @Override
    public void setPosition(final Position pos) {
        this.position = pos;
    }

    /** {@inheritDoc}*/
    @Override
    public int getTimesEaten() {
        return this.eatenCounter;
    }

    /** {@inheritDoc}*/
    @Override
    public void incrementEatenCounter() {
        this.eatenCounter++;
    }

}
