package Game;

public class SettingsManager {
    private static final SettingsManager instance = new SettingsManager();

    private int defaultGoalSize = 160;
    private int updatesPerSecond = 64;
    private int framesPerSecond = 64;

    private SettingsManager() {}

    public static SettingsManager getInstance() {
        return instance;
    }

    public int getDefaultGoalSize() {
        return defaultGoalSize;
    }

    public void setDefaultGoalSize(int defaultGoalSize) {
        this.defaultGoalSize = defaultGoalSize;
    }

    public int getUpdatesPerSecond() {
        return updatesPerSecond;
    }

    public void setUpdatesPerSecond(int updatesPerSecond) {
        this.updatesPerSecond = updatesPerSecond;
    }

    public int getFramesPerSecond() {
        return framesPerSecond;
    }

    public void setFramesPerSecond(int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
    }
}
