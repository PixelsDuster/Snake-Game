package config;

/**
 * Utility class containing constants used throughout the projects,
 * including board dimention, timer delay, and tile size
 */
public final class GameConfig {
    public static final int TILE_SIZE = 25;
    public static final int BOARD_WIDTH = 600;
    public static final int BOARD_HEIGHT = 600;
    public static final int TIMER_DELAY = 100;

    private GameConfig() {
        // Prevent instantiation
    }
}
