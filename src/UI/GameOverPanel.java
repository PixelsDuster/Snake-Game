package UI;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Game over panel for the Snake game.
 * Provides options to restart the game or exit to the main menu.
 */
public class GameOverPanel extends JPanel{

    private JLabel gameOverLabel;
    private JButton restartButton;
    private JButton exitButton;

    private Runnable onRestartGame;
    private Runnable onExit;

    /**
     * Constructs the game over panel.
     * @param onRestartGame Runnable to execute when "Restart" is pressed
     * @param onExit Runnable to execute when "Exit" is pressed
     */
    public GameOverPanel(Runnable onRestartGame, Runnable onExit){
        this.onRestartGame = onRestartGame;
        this.onExit = onExit;
        initialize();
    }
    
    /**
     * Initializes the panel and its components.
     */
    private void initialize(){
        this.setBackground(Color.GRAY);

        this.gameOverLabel = new JLabel("GAMEOVER!");

        // Restart Button
        this.restartButton = new JButton("Restart");
        restartButton.setMnemonic(KeyEvent.VK_R);
        restartButton.setFont(new Font("Arial", Font.PLAIN, 24));
        restartButton.setMargin(new Insets(10, 10, 10, 10));
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(onRestartGame != null){
                    onRestartGame.run();
                }
            }
        });
        
        // Exit Button
        this.exitButton = new JButton("Exit");
        exitButton.setMnemonic(KeyEvent.VK_E);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 24));
        exitButton.setMargin(new Insets(10, 10, 10, 10));
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(onExit != null){
                    onExit.run();
                }
            }
        });
        
        this.add(gameOverLabel);
        this.add(restartButton);
        this.add(exitButton);
    }
}
