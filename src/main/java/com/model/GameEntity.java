package main.java.com.model;

import main.java.com.utility.Position;

/**
 * A generic entity of the game that has a position on the game map.
 *
 */
public interface GameEntity {

    /**
     * 
     * @return the entity's position.
     */
    Position getPosition();
}
