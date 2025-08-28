package model;

import config.GameConfig;
import java.util.ArrayList;
import java.util.List;

/*
 * Contains the Logic for the Snake.
 * Handles movement and collision detection
 */
public class Snake {
    private Tile snakeHead;
    private List<Tile> snakeBody;
    private Direction direction;

    public Snake(){
        this.snakeHead = new Tile(5, 5);
        this.snakeBody = new ArrayList<>();
        this.direction = Direction.DOWN;
    }

    public Tile getHead(){
        return this.snakeHead;
    }

    public List<Tile> getBody(){
        return this.snakeBody;
    }

    public Direction getDirection(){
        return this.direction;
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    /**
     * Moves the Snake one tile in the current direction, then check for collision
     * 
     * @return true if the snake collides with the boarder or itself
     */
    public boolean move(){
        // Snake Body
        for(int i = this.snakeBody.size() - 1; i >= 0; i--){
            Tile snakePart = this.snakeBody.get(i);
            if(i == 0){
                snakePart.x = this.snakeHead.x;
                snakePart.y = this.snakeHead.y;
            }
            else {
                Tile prevSnakePart = this.snakeBody.get(i - 1);
                snakePart.x = prevSnakePart.x;
                snakePart.y = prevSnakePart.y;
            }
        }

        // Snake Head
        this.snakeHead.x += direction.dx;
        this.snakeHead.y += direction.dy;

        // Game Over condition
        // Collision with Body
        boolean hitBody = this.snakeBody.stream()
                    .anyMatch(part -> this.snakeHead.collision(part));

        // Collide with Boarder
        boolean hitBoarder = this.snakeHead.x * GameConfig.TILE_SIZE < 0 || 
                             this.snakeHead.x * GameConfig.TILE_SIZE > GameConfig.BOARD_WIDTH || 
                             this.snakeHead.y * GameConfig.TILE_SIZE < 0 ||
                             this.snakeHead.y * GameConfig.TILE_SIZE > GameConfig.BOARD_HEIGHT;

        return hitBody || hitBoarder;
    }
}
