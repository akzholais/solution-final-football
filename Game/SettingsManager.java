package Game;

public class SettingsManager {
    private static final SettingsManager instance = new SettingsManager();

    private int defaultGoalSize = 160;


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

}
