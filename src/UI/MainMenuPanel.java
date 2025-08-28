package UI;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Main menu panel for the Snake game.
 * Provides buttons to start the game and view the leaderboard.
 */
public class MainMenuPanel extends JPanel{

    private JButton startButton;
    private JButton leaderboardButton;

    private Runnable onStartGame;
    private Runnable onShowLeaderboard;

    /**
     * Constructs the main menu panel.
     * @param onStartGame Runnable to execute when "Start Game" is pressed
     * @param onShowLeaderboard Runnable to execute when "Leaderboard" is pressed
     */
    public MainMenuPanel(Runnable onStartGame, Runnable onShowLeaderboard){
        this.onStartGame = onStartGame;
        this.onShowLeaderboard = onShowLeaderboard;
        initialize();
    }
    
    /**
     * Initializes the panel and its components.
     */
    private void initialize(){
        this.setBackground(Color.GRAY);

        // Start Button
        this.startButton = new JButton("Start Game");
        startButton.setFocusable(true);
        startButton.setMnemonic(KeyEvent.VK_S);
        startButton.setFont(new Font("Arial", Font.PLAIN, 24));
        startButton.setMargin(new Insets(10, 10, 10, 10));
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(onStartGame != null){
                    onStartGame.run();
                }
            }
        });
        
        // Leaderboard Button
        this.leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setMnemonic(KeyEvent.VK_L);
        leaderboardButton.setFont(new Font("Arial", Font.PLAIN, 24));
        leaderboardButton.setMargin(new Insets(10, 10, 10, 10));
        leaderboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(onShowLeaderboard != null){
                    onShowLeaderboard.run();
                }
            }
        });

        this.add(startButton);
        this.add(leaderboardButton);
    }

}
