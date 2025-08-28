package game;

import javax.swing.*;
import java.awt.CardLayout;

import UI.MainMenuPanel;
import UI.GameOverPanel;
import UI.LeaderboardPanel;
import config.GameConfig;

/*
 * Main entry point for the Project.
 * Sets up the JFrame and starts the game.
 */
public class App {

    /**
     * Initializes the main window and panels, and launches the application.
     * @param args Command-line arguments (not used)
     * @throws Exception if an error occurs during startup
     */
    public static void main(String[] args) throws Exception {
        int boardWidth = GameConfig.BOARD_WIDTH;
        int boardHeight = GameConfig.BOARD_HEIGHT;

        JFrame frame = new JFrame("Snake");
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        mainPanel.setPreferredSize(new java.awt.Dimension(boardWidth, boardHeight));

        // Create and show a new game instance
        Runnable showNewGame = () -> {
            SnakeGame newGame = new SnakeGame(() -> cardLayout.show(mainPanel, "gameOver"));
            mainPanel.add(newGame, "game");
            cardLayout.show(mainPanel, "game");
            newGame.requestFocusInWindow();
        };

        MainMenuPanel mainMenuPanel = new MainMenuPanel(
            showNewGame,                                            // start button
            () -> cardLayout.show(mainPanel, "leaderboard")    // leaderboard button
        );

        GameOverPanel gameOverPanel = new GameOverPanel(
            showNewGame,                                            // Restart the game
            () -> cardLayout.show(mainPanel, "menu")           // back to menu
        );

        LeaderboardPanel leaderBoardPanel = new LeaderboardPanel(
            () -> cardLayout.show(mainPanel, "menu")           // back to menu
        );

        mainPanel.add(mainMenuPanel, "menu");
        mainPanel.add(gameOverPanel, "gameOver");
        mainPanel.add(leaderBoardPanel, "leaderboard");

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        cardLayout.show(mainPanel, "menu");
    }
}
