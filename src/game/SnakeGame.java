package game;

import config.GameConfig;

import model.Direction;
import model.Food;
import model.Snake;
import model.Tile;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.*;

/*
 * Main game panel for the Game
 * Handles rendering, game logic, and user input
 */
public class SnakeGame extends JPanel implements ActionListener, KeyListener{
    // Snake
    Snake snake;
    // Food
    Food food;
    // Game Logic
    Timer gameLoop;
    boolean gameOver = false;
    private Runnable onGameOver;

    /**
     * Constructs a new SnakeGame panel.
     * @param onGameOver Runnable to execute when the game ends
     */
    public SnakeGame(Runnable onGameOver){
        this.onGameOver = onGameOver;

        setPreferredSize(new Dimension(GameConfig.BOARD_WIDTH, GameConfig.BOARD_HEIGHT));
        setBackground(Color.BLACK);

        addKeyListener(this);
        setFocusable(true);

        this.snake = new Snake();
        this.food = new Food();
        this.food.placeFood(GameConfig.BOARD_HEIGHT, GameConfig.BOARD_WIDTH, GameConfig.TILE_SIZE, this.snake);
        this.gameLoop = new Timer(GameConfig.TIMER_DELAY, this);

        gameLoop.start();
    }

    /**
     * Paints the current game state on the panel.
     * @param g Graphics context
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawGame(g);
    }

    /**
     * Paint the game components: Grid, Food, Snake, and score.
     * @param g Graphics context
     */
    public void drawGame(Graphics g){
        // Grid
        for(int i = 0; i < GameConfig.BOARD_WIDTH / GameConfig.TILE_SIZE; i++){
            // (x1, y1, x2, y2)
            g.drawLine(i * GameConfig.TILE_SIZE, 0, i * GameConfig.TILE_SIZE, GameConfig.BOARD_HEIGHT);
            g.drawLine(0, i * GameConfig.TILE_SIZE, GameConfig.BOARD_WIDTH, i * GameConfig.TILE_SIZE);
        }

        // Food
        g.setColor(Color.RED);
        g.fill3DRect(food.getFood().x * GameConfig.TILE_SIZE, food.getFood().y * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, true);

        // Snake Head
        g.setColor(Color.GREEN);
        g.fill3DRect(this.snake.getHead().x * GameConfig.TILE_SIZE, this.snake.getHead().y * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, true);

        // Snake Body
        for(int i = 0; i < this.snake.getBody().size(); i++){
            Tile snakePart = this.snake.getBody().get(i);
            g.fill3DRect(snakePart.x * GameConfig.TILE_SIZE, snakePart.y * GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE, true);
        }

        // Score
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        if(gameOver){
            g.setColor(Color.RED);
            g.drawString("GAME OVER: " + this.snake.getBody().size(), GameConfig.TILE_SIZE - 16, GameConfig.TILE_SIZE);
        }
        else{
            g.drawString("Score: " + this.snake.getBody().size(), GameConfig.TILE_SIZE - 16, GameConfig.TILE_SIZE);
        }
    }

    /*
     * Moves the Snake, updating Food position after eating it, and check for collision  
     */
    public void move(){
        // Eating food
        if(this.snake.getHead().collision(food.getFood())){
            this.snake.getBody().add(new Tile(food.getFood().x, food.getFood().y));
            this.food.placeFood(GameConfig.BOARD_HEIGHT, GameConfig.BOARD_WIDTH, GameConfig.TILE_SIZE, this.snake);
        }
        // Moving the snake
        gameOver = this.snake.move();
    }

    /**
     * Called by the game timer to update and repaint the game.
     * @param e Action event from the timer
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
        if(gameOver){
            gameLoop.stop();
            saveScore();
            if(onGameOver != null){
                onGameOver.run();
            }
        }
    }

    /**
     * Prompts the user for a name and saves the score to the leaderboard.
     */
    public void saveScore(){
        String name = "";
        do{
            name = JOptionPane.showInputDialog("Enter your Name: ");
            if(name == null) return;
        }while(!verif(name));
        
        try (FileWriter writer = new FileWriter(Paths.get("Leaderboard.txt").toFile(), true)) {
            writer.write(name + "," + this.snake.getBody().size() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * Verifies that the entered name is valid (not empty and does not contain banned characters).
     * @param name The name to verify
     * @return true if valid, false otherwise
     */
    public boolean verif(String name){
        if(name == null) return false;
        name = name.trim();
        if(name.isEmpty()) return false;
        String bannedChars = ",;.:/'\"";
        for (int i = 0; i < name.length(); i++) {
            if (bannedChars.indexOf(name.charAt(i)) != -1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Handles key presses control the Snakes's direction
     * @param e Key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        Direction current = snake.getDirection();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (current != Direction.DOWN) snake.setDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                if (current != Direction.UP) snake.setDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                if (current != Direction.RIGHT) snake.setDirection(Direction.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                if (current != Direction.LEFT) snake.setDirection(Direction.RIGHT);
                break;
        }
    }

    /*
     * these are not needed, need to be here because of KeyListener-interface 
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

}
