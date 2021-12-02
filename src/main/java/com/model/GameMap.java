package main.java.com.model;

import java.util.Set;

import main.java.com.utility.Position;

/**
 * The map of the game in which all of the game entities are placed and are able to move.
 *
 */
public interface GameMap {

    /**
     * 
     * @return a set of all of the cells of the map (all of the possible coordinates).
     */
   Set<Position> getAllCells();

   /**
    * 
    * @return a set of all the cells that are not currently occupied by one of the game's entities.
    */
   Set<Position> getFreeCells();

   /**
    * 
    * @return a set containing the positions of the walls in the map.
    */
   Set<Position> getWalls();

   /**
    * Reset all the map and the game entities to their initial status.
    */
   void resetMap();

   /**
    * Update the status of the apple in the game map.
    */
   void updateApple();

   /**
    * Update the status of the snake in the game map.
    */
   void updateSnake();

   /**
    * 
    * @return snake.
    */
   SnakeEntity getSnake();

   /**
    * 
    * @return the apple.
    */
   EatableEntity getApple();
}
