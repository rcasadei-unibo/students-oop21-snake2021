package main.java.com.model;

import main.java.com.utility.Position;

public class Apple implements EatableEntity {

    private static final int INITIAL_POINTS = 50;
    private static final int POINTS_CHANGE = 50;
    private static final int APPLES_MULT = 20;

    private Position position;
    private int eatenCounter;
    private int pointsValue;

    public Apple(final Position pos) {
        position = pos;
        eatenCounter = 0;
        pointsValue = INITIAL_POINTS;
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

    /** {@inheritDoc} */
    public void resetTimesEaten() {
        this.eatenCounter = 0;
    }

    /** {@inheritDoc}*/
    @Override
    public void incrementEatenCounter() {
        this.eatenCounter++;
        if (this.eatenCounter % APPLES_MULT == 0) {
            this.pointsValue += POINTS_CHANGE; // The points value for each apple eaten increments by 50 every twenty apples eaten.
        }
    }

    /** {@inheritDoc} */
    @Override
    public int getPointsValue() {
        return this.pointsValue;
    }

}
