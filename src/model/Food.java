package model;

import java.util.Random;

/**
 * Contains the Logic for Food tile.
 * Including placing it in random location and collision avoidance  
 */
public class Food {
    private Tile food;
    private Random random;

    public Food() {
        this.food = new Tile(5, 5);
        this.random = new Random();
    }

    public Tile getFood(){
        return this.food;
    } 

    /**
     * Place the Food Tile in a random position, also checks for collision with the Snake
     * 
     * @param boardHeight height of the game board
     * @param boardWidth width of the game board
     * @param tileSize size of each tile
     * @param snake current Snake 
     */
    public void placeFood(int boardHeight, int boardWidth, int tileSize, Snake snake){
        do{
            food.x = random.nextInt(boardWidth / tileSize);
            food.y = random.nextInt(boardHeight / tileSize);

        }while(snake.getHead().collision(food) || 
               snake.getBody().stream().anyMatch(part -> part.collision(food)));
    }
}
