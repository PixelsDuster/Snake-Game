package UI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Leaderboard panel for the Snake game.
 * Displays the list of player names and scores.
 */
public class LeaderboardPanel extends JPanel{
    
    private JLabel leaderboardLabel;
    private Runnable onExit;

    /**
     * Constructs the leaderboard panel.
     * @param onExit Runnable to execute when "Exit" is pressed
     */
    public LeaderboardPanel(Runnable onExit){
        this.onExit = onExit;
        initialize();
    }

    /**
     * Initializes the panel and loads leaderboard data.
     */
    private void initialize(){
        this.setLayout(new BorderLayout(10, 10));
        
        // Title
        this.leaderboardLabel = new JLabel("LEADERBOARD");
        leaderboardLabel.setFont(new Font("Courier New", Font.BOLD, 50));
        this.add(leaderboardLabel, BorderLayout.NORTH);

        // Table Panel
        JPanel tablePanel = new JPanel(new GridLayout(0, 2, 20, 5));
        
        try {
            // List<String[]> leaderboard = Files.lines(Paths.get(System.getProperty("user.dir"), "Leaderboard.txt")) // saver file directory
            List<String[]> leaderboard = Files.lines(Paths.get("Leaderboard.txt"))
                .map(line -> line.split(","))
                .filter(parts -> parts.length == 2)
                .sorted((a, b) -> Integer.compare(Integer.parseInt(b[1].trim()), Integer.parseInt(a[1].trim())))
                .toList();
            
            for(String[] entry : leaderboard){
                JLabel name = new JLabel(entry[0]);
                name.setFont(new Font("Courier New", Font.PLAIN, 20));
                name.setHorizontalAlignment(JLabel.LEFT);

                JLabel score = new JLabel(entry[1]);
                score.setFont(new Font("Courier New", Font.PLAIN, 20));
                score.setHorizontalAlignment(JLabel.RIGHT);
                
                tablePanel.add(name);
                tablePanel.add(score);
            }
        } catch (Exception e) {
            System.out.println("Error reading Leaderboard: " + e.getMessage());
        }

        this.add(tablePanel, BorderLayout.CENTER);

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            if(onExit != null){
                onExit.run();
            }
        });

        this.add(exitButton, BorderLayout.SOUTH);
    }

    /**
     * Reloads leaderboard data every time the panel is shown.
     * @param aFlag true to make visible, false to hide
     */
    @Override
    public void setVisible(boolean aFlag) {
        super.setVisible(aFlag);
        if (aFlag) {
            removeAll();
            initialize();
            revalidate();
            repaint();
        }
    }
}
