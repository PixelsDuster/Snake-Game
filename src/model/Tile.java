package model;

/*
 * Represent a gameboard Tile
 */
public class Tile{
    public int x;
    public int y;
    
    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Check whether this tile is colliding with another tile
     * 
     * @param tile 
     * @return true if this tile collided with another tile
     */
    public boolean collision(Tile tile){
        return this.x == tile.x && this.y == tile.y;
    }
}